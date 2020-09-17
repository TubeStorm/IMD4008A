package favourdiokpo.tuorial1.tutorial5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


@RequiresApi(api = Build.VERSION_CODES.O)

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL = "default";
    private static final int NOTIFICATION_DEFAULT = 1100;
    TextInputEditText notificationText;
    NotificationManager notificationManager;
    TextInputEditText newnotificationID;
    int newId;
    EditText delayTime;
    int newTimedelay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationText = findViewById(R.id.notification_text);
        newnotificationID = findViewById(R.id.newId2);
        delayTime = findViewById(R.id.timeDelay);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel chn = new NotificationChannel(CHANNEL,
                "CHANNEL",
                NotificationManager.IMPORTANCE_HIGH);

        notificationManager.createNotificationChannel(chn);

    }

    public void notificationButton(View view){

        newId = Integer.parseInt(newnotificationID.getText().toString());
        sendNotification(newId, "Notification");

    }

    public void scheduleButton(View view){
        newId = Integer.parseInt(newnotificationID.getText().toString());
        newTimedelay = Integer.parseInt(delayTime.getText().toString());
        scheduleNotification( newId, "Notification", newTimedelay);

    }

    public Notification buildNotification(String title, String body){
        Notification.Builder nb = new Notification.Builder(getApplicationContext(), CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.noti)
                .setAutoCancel(true);

        Notification noti = nb.build();
        return noti;
    }

    public void sendNotification(int id, String title){
        Notification noti = buildNotification(title, notificationText.getText().toString());
        if (noti != null){
            notificationManager.notify(id, noti);
        }
    }

    public void scheduleNotification(int id, String title, int delay){
        Notification noti = buildNotification(title, notificationText.getText().toString());

        Intent notificationIntent = new Intent(this, NotificationBroadcaster.class);
        notificationIntent.putExtra(NotificationBroadcaster.NOTIFICATION_ID, id);
        notificationIntent.putExtra(NotificationBroadcaster.NOTIFICATION, noti);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long myAlarmTime = System.currentTimeMillis()+delay;
        AlarmManager al = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        al.set(AlarmManager.RTC_WAKEUP, myAlarmTime, pendingIntent);
    }


}
