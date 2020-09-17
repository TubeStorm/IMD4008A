package com.example.tutorial08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.gesture.Gesture;
import android.gesture.Prediction;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnGesturePerformedListener,
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private GestureLibrary gLibrary;
    TextView inputText;
    ImageView imageView;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.Commands);
        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);

        inputText = findViewById(R.id.myTextview);
        gLibrary = GestureLibraries.fromRawResource(this, R.raw.gesture);
        gLibrary.load();

        GestureOverlayView gOverlay = (GestureOverlayView) findViewById(R.id.gOverlay);
        gOverlay.addOnGesturePerformedListener(this);

        imageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent e)
            {
                mDetector.onTouchEvent(e);
                return true;
            }

        });
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

        ArrayList<Prediction> predictions = gLibrary.recognize(gesture);

        if(predictions.size() > 0 && predictions.get(0).score > 3)
        {
            String action = predictions.get(0).name;
            String newText = inputText.getText() + action;
            inputText.setText(newText);
            Toast.makeText(this, action, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        String text = inputText.getText() +".";
        inputText.setText(text);
        //Toast.makeText(this, "You Have Double Tapped", Toast.LENGTH_SHORT).show();
        return true;
    }


    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        String text = inputText.getText() +",";
        inputText.setText(text);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        String text = inputText.getText() +"\n";
        inputText.setText(text);

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(velocityX > 0)
        {String text = inputText.getText() +" ";
        inputText.setText(text);}

        if (velocityX < 0){
            String text = inputText.getText().toString();
            int input = text.length();
            inputText.setText(text.substring(0,input-1));
        }

        return true;
    }
}
