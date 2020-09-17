package favourdiokpo.tuorial1.tutorial2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import the widget library
import android.view.View;
import android.widget.*;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.RatingBar;



public class MainActivity extends AppCompatActivity {

    TableLayout mainTable;
    TextView[] txts = new TextView[6];
    Button[] btns = new Button[2];
    Switch swtch;
    RadioGroup radioGroup;
    View radioColor;
    RadioButton[] rbs = new RadioButton[3];
    CheckBox cb;
    RatingBar ratn;
    String newString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainTable = findViewById(R.id.maintable);

        txts[0] = findViewById(R.id.firsttext);
        txts[1] = findViewById(R.id.secondtext);
        txts[2] = findViewById(R.id.thirdtext);
        txts[3] = findViewById(R.id.fourthtext);
        txts[4] = findViewById(R.id.fifthtext);
        txts[5] = findViewById(R.id.sixthtext);

        btns[0] = findViewById(R.id.inc);
        btns[1] = findViewById(R.id.dec);

        swtch = findViewById(R.id.mySwitch);

        radioGroup = findViewById(R.id.rg);
        radioColor = findViewById(R.id.rview);
        rbs[0] = findViewById(R.id.red);
        rbs[1] = findViewById(R.id.blue);
        rbs[2] = findViewById(R.id.green);
        cb = findViewById(R.id.check);

        ratn = findViewById(R.id.rating);

        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mainTable.setBackgroundColor(Color.GRAY);
                    txts[0].setTextColor(Color.WHITE);
                    txts[1].setTextColor(Color.WHITE);
                    txts[2].setTextColor(Color.WHITE);
                    txts[3].setTextColor(Color.WHITE);
                    txts[4].setTextColor(Color.WHITE);

                } else {
                    mainTable.setBackgroundColor(Color.WHITE);
                    txts[0].setTextColor(Color.BLACK);
                    txts[1].setTextColor(Color.BLACK);
                    txts[2].setTextColor(Color.BLACK);
                    txts[3].setTextColor(Color.BLACK);
                    txts[4].setTextColor(Color.BLACK);
                }
            }
        });

        ratn.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float a, boolean b) {
                txts[5].setText(String.valueOf(a));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        txts[5].setText("1");
                        break;
                    case 2:
                        txts[5].setText("2");
                        break;
                    case 3:
                        txts[5].setText("3");
                        break;
                    case 4:
                        txts[5].setText("4");
                        break;
                    case 5:
                        txts[5].setText("5");
                        break;
                    default:
                        txts[5].setText("");
                }
            }
        });


    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.red:
                if (checked)
                    radioColor.setBackgroundColor(Color.RED);
                break;
            case R.id.blue:
                if (checked)
                    radioColor.setBackgroundColor(Color.BLUE);
                break;
            case R.id.green:
                if (checked)
                    radioColor.setBackgroundColor(Color.GREEN);
                break;
        }
    }


    public void onCheckBoxClicked(View view) {

        //is checkbox checked?
        boolean c = ((CheckBox) view).isChecked();

        //if checked, change fifth text
        if (c) {
            txts[4].setText("Checked");
        } else {
            txts[4].setText("Un-Checked");
        }

    }


    public void buttonClick(View v) {
        if (v == btns[0]) {
            txts[2].setText(Integer.toString(Integer.parseInt(txts[2].getText().toString()) + 1));
        } else if (v == btns[1]) {
            txts[2].setText(Integer.toString(Integer.parseInt(txts[2].getText().toString()) - 1));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        s = txts[2].getText().toString();

        super.onSaveInstanceState(outState);
        outState.putString("myText", newString);
        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        newString = savedInstanceState.getString("myText");
    }
}






