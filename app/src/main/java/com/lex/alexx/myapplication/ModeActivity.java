package com.lex.alexx.myapplication;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by alex on 31.07.2016.
 */
public class ModeActivity extends AppCompatActivity {

    Button save;
    EditText one;

    int timeone;
    SharedPreferences sp;
    NumberPicker number;
    NumberPicker number2;
    NumberPicker number3;
    NumberPicker number4;
    TextView spo;

TextView ot ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode);
        save = (Button) findViewById(R.id.button);
ot= (TextView) findViewById(R.id.textView);
     spo=(TextView)findViewById(R.id.textView2);
        number = (NumberPicker) findViewById(R.id.numberPicker);
        number2 = (NumberPicker) findViewById(R.id.numberPicker2);
        number3 = (NumberPicker) findViewById(R.id.numberPicker3);
        number4 = (NumberPicker) findViewById(R.id.numberPicker4);

        number.setMinValue(0);
        number2.setMinValue(0);
        number3.setMinValue(0);
        number4.setMinValue(0);

        number.setMaxValue(23);
        number2.setMaxValue(59);
        number3.setMaxValue(23);
        number4.setMaxValue(23);


    SharedPreferences shar= getSharedPreferences("count",MODE_PRIVATE);
    number.setValue(shar.getInt("time",3));
    number2.setValue(shar.getInt("time2",0));
    number3.setValue(shar.getInt("time3",8));
    number4.setValue(shar.getInt("time4",21));

        ot.setText(getString(R.string.othertext)+ number.getValue()+getString(R.string.othertext2) +number2.getValue()+getString(R.string.othertext3));
        spo.setText(getString(R.string.othertext4)+ number3.getValue()+getString(R.string.othertext5) +number4.getValue()+getString(R.string.othertext6));
    }





    public void Iss(View view) {

// получаем время и кидаем в файл
        Toast.makeText(getApplicationContext(),getString(R.string.modesavetoast),Toast.LENGTH_SHORT).show();
  ot.setText(getString(R.string.othertext)+ number.getValue()+getString(R.string.othertext2) +number2.getValue()+getString(R.string.othertext3));
       spo.setText(getString(R.string.othertext4)+ number3.getValue()+getString(R.string.othertext5) +number4.getValue()+getString(R.string.othertext6));

    if(number.getValue()==0&&number2.getValue()==0){}
    else{
        try{MainActivity a = new MainActivity();
      a.onOff.setBackgroundResource(R.drawable.buttonon);}catch (Exception e){};



        timeone = number.getValue();
        sp = getSharedPreferences("count", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("time", timeone);
        ed.putInt("time2", number2.getValue());

        ed.putInt("time3", number3.getValue());
        ed.putInt("time4", number4.getValue());
        ed.putBoolean("boolean",true);
        long sys= System.currentTimeMillis();
        ed.putLong("timesys",sys);
        ed.apply();

        scheduleNotification();
    } }


    public void scheduleNotification() {

        //удаляем старое уведомление
        NotificationManager nn = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nn.cancelAll();

        // вытаскиваем данные из файла
        SharedPreferences ss = getSharedPreferences("count", MODE_PRIVATE);
        int xx = ss.getInt("time", 3);
        int xx2 = ss.getInt("time2", 0);
        int xx3 = ss.getInt("time3", 8);
        int xx4 = ss.getInt("time4", 21);


        Calendar cal = Calendar.getInstance();

        int h = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        String s = Integer.toString(h) + "" + minute;
        


        // создаем интент и отправляем первый будильник
        Intent notificationIntent = new Intent("Spy");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        int minutehour = h * 60 + minute;
        long one = xx * 60 + xx2;
        long futureInMillis = System.currentTimeMillis() + one * 60 * 1000;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // удаляем ранее сохраненый будильник
        alarmManager.cancel(pendingIntent);
        long zz = (long) (xx3 * 60 - minutehour) * 60 * 1000;
        int count = (24 * 60 - minutehour + xx3 * 60) * 60 * 1000;
        long zz22 = (long) count;

        int z = (minutehour + xx * 60 + xx2) / 60;


        if (xx4 > xx3) {
            if (h >= xx3 && z < xx4) {

                alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            }
            else if (h >= xx3 && z >= xx4) {


                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz22, pendingIntent);
            } else {

                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz, pendingIntent);

            }


        }
        if (xx4 < xx3) {

            if (h >= xx3 || z <= xx4) {

                alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);

            }

            else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz, pendingIntent);

            }

        }
        if (xx4 == xx3) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);

        }


    }


}