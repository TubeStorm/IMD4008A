package FavourDiokpo.Tuorial1.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import the widget library
import android.view.View;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    //create the elements and assign the right "button" or "textview"
    TextView counter;
    Button inc;
    Button dec;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect the elements to the ID's we made
        counter = findViewById(R.id.counter);
        inc = findViewById(R.id.increment);
        dec = findViewById(R.id.decrement);
        reset = findViewById(R.id.reset);

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //work on converting to string > int and then int > string
                String counterA;
                counterA = counter.getText().toString();
                int counterInt = Integer.parseInt(counterA);
                counterInt += 1;
                counterA = Integer.toString(counterInt);
                counter.setText(counterA);
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //work on converting to string > int and then int > string
                String counterA;
                counterA = counter.getText().toString();
                int counterInt = Integer.parseInt(counterA);
                counterInt -= 1;
                counterA = Integer.toString(counterInt);
                counter.setText(counterA);
            }
        });


        reset.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                String counterA;
                counterA = counter.getText().toString();
                int counterInt = Integer.parseInt(counterA);
                counterInt = 0;
                counterA = Integer.toString(counterInt);
                counter.setText(counterA);
                return false;
            }
        });
    }
}




