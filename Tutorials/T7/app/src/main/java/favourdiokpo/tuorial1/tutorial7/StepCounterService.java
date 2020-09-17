package favourdiokpo.tuorial1.tutorial7;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;


public class StepCounterService extends Service implements StepListener, SensorEventListener {
    public static final String REQUEST_DATA_INTENT = "REQUEST_DATA";
    public static final String REFRESH_DATA_INTENT = "REFRESH_DATA";
    public static final String STEP_COUNT = "STEP_COUNT";

    private static final String SERVICE_NAME = "Step Counter";
    private static final String CHANNEL_ID = "STEP_COUNTER_CHANNEL";
    private static final int NOTIFICATION_ID = 1;

    private NotificationManager notificationManager;

    private StepDetector stepDetector;
    private SensorManager sensorManager;

    private int oldStepCount;
    private int stepCount;

    private DataRequestReceiver dataRequestReciever;

    @Override
    public void onCreate(){

        oldStepCount = -1;
        stepCount = 0;

        dataRequestReciever = new DataRequestReceiver();
        IntentFilter intentFilter = new IntentFilter(StepCounterService.REFRESH_DATA_INTENT);
        registerReceiver(dataRequestReciever, intentFilter);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        createNotificationChannel();

        Notification notification = getNotification();
        startForeground(NOTIFICATION_ID, notification);

        registerSensors();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        if(sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        unregisterReceiver(dataRequestReciever);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        switch(event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                stepDetector.updateAccel(
                        event.timestamp, event.values[0],event.values[1],event.values[2]);
                break;
            case Sensor.TYPE_STEP_COUNTER:
                if (oldStepCount < 0 ){
                    oldStepCount = (int) event.values[0];
                    break;
                }

                stepCount = (int) event.values[0] - oldStepCount;
                sendRefreshData();
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                stepDetected();
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void stepDetected(){
        stepCount++;
        sendRefreshData();

    }


    private void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    SERVICE_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            serviceChannel.setSound(null,null);

            notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null) {
                notificationManager.createNotificationChannel(serviceChannel);
            }
        }
    }

    private Notification getNotification(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);
        if (Build.VERSION.SDK_INT < 26){
            return new Notification();
        }

        return new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(SERVICE_NAME)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();
    }


    private void registerSensors(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager != null){
            Sensor sensor;
            PackageManager packageManager = getPackageManager();

            if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)){
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            }
            else if(packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)){
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            }
            else{
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                stepDetector = new StepDetector();
                stepDetector.registerListener(this);
            }

            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    public void sendRefreshData(){
        Intent refreshDataIntent  = new Intent(REFRESH_DATA_INTENT);
        refreshDataIntent.putExtra(STEP_COUNT, stepCount);
        sendBroadcast(refreshDataIntent);
    }


    private class DataRequestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent){
            if(Objects.equals(intent.getAction(), StepCounterService.REQUEST_DATA_INTENT)){
                sendRefreshData();
            }
        }

    }

}



