package com.ersubhadip.wakemeup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

import static android.media.RingtoneManager.TYPE_ALARM;
import static android.media.RingtoneManager.TYPE_NOTIFICATION;
import static android.media.RingtoneManager.TYPE_RINGTONE;
import static android.widget.Toast.LENGTH_LONG;

public class SetTimeActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private TextClock textClock;
    private FloatingActionButton fabOn,fabOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        timePicker=findViewById(R.id.timePicker);
        textClock=findViewById(R.id.timeClock);
        fabOff=findViewById(R.id.offFab);
        fabOn=findViewById(R.id.onFab);


        fabOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ringtone r= RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(TYPE_RINGTONE));


                //timePicker returns in 24 hr system and textClock in 1 hr system


                Timer t=new Timer();
                t.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        if(textClock.toString().equals(AlarmTime())){

                            r.play();

                        }else{

                            r.stop();
                        }

                    }
                },0,1000);
                Toast.makeText(SetTimeActivity.this, "Success", LENGTH_LONG).show();

            }
        });
        AlertDialog.Builder b=new AlertDialog.Builder(SetTimeActivity.this);

        b.setCancelable(true);
        b.setTitle("Alert!");
        b.setMessage("Do You Want to Exit ?");
        b.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        b.setNegativeButton("Set Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        AlertDialog dialog=b.create();

        fabOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.show();



            }
        });




    }

    private String AlarmTime() {
        String AlarmTime="";
        Integer hr =timePicker.getHour();
        Integer min =timePicker.getMinute();
        String temp=min.toString();
        if(min<10){

            temp="0"+temp;

        }

        if(hr>12){

            hr=hr-12;
            AlarmTime=hr.toString().concat(":").concat(min.toString()).concat(" PM");


        }else{

            AlarmTime=hr.toString().concat(":").concat(min.toString()).concat(" AM");
        }

        return  AlarmTime;

    }
}