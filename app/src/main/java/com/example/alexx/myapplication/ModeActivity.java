package com.example.alexx.myapplication;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode);
        save = (Button) findViewById(R.id.button);

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
    number.setValue(shar.getInt("time",0));
    number2.setValue(shar.getInt("time2",0));
    number3.setValue(shar.getInt("time3",0));
    number4.setValue(shar.getInt("time4",0));


    }


    public void Iss(View view) {

// получаем время и кидаем в файл

        timeone = number.getValue();
        sp = getSharedPreferences("count", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("time", timeone);
        ed.putInt("time2", number2.getValue());

        ed.putInt("time3", number3.getValue());
        ed.putInt("time4", number4.getValue());
        ed.putBoolean("boolean",true);
        ed.putString("texttitle","Пора кушать");
        ed.putString("text","улыбнись! и преятного тебе аппетита");
        ed.apply();
        long sys= System.currentTimeMillis();
        ed.putLong("timesys",sys);
        scheduleNotification();
    }


    public void scheduleNotification() {

        //удаляем старое уведомление
        NotificationManager nn = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nn.cancelAll();

        // вытаскиваем данные из файла
        SharedPreferences ss = getSharedPreferences("count", MODE_PRIVATE);
        int xx = ss.getInt("time", 0);
        int xx2 = ss.getInt("time2", 0);
        int xx3 = ss.getInt("time3", 0);
        int xx4 = ss.getInt("time4", 0);


        Calendar cal = Calendar.getInstance();

        int h = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        String s = Integer.toString(h) + "" + minute;
        save.setText(s);
        int minutehour = h * 60 + minute;

        // создаем интент и отправляем первый будильник
        Intent notificationIntent = new Intent("Spy");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

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
            if (z >= xx3 && z < xx4) {
                Toast t = Toast.makeText(getApplicationContext(),"1 "+zz+" "+zz22+" "+z,Toast.LENGTH_SHORT);
                t.show();
                alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            }
            else if (z >= xx3 && z >= xx4) {
               Toast t2 = Toast.makeText(getApplicationContext(),"2 "+zz+" "+zz22+" "+z,Toast.LENGTH_SHORT);
                t2.show();

                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz22, pendingIntent);
            } else {
                Toast t3 = Toast.makeText(getApplicationContext(),"3 "+zz+" "+ zz22+ " "+ z ,Toast.LENGTH_SHORT);
                t3.show();
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz, pendingIntent);

            }


        }
        if (xx4 < xx3) {

            if (z >= xx3 || z <= xx4) {

                alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
                Toast t4 = Toast.makeText(getApplicationContext(),"4 "+futureInMillis,Toast.LENGTH_SHORT);
t4.show();
            }

            else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz22, pendingIntent);
Toast t5 = Toast.makeText(getApplicationContext(),"5 " +zz ,Toast.LENGTH_SHORT);
                t5.show();
            }

        }
        if (xx4 == xx3) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            Toast t6 = Toast.makeText(getApplicationContext(),"6 " + futureInMillis,Toast.LENGTH_SHORT);
t6.show();
        }


    }


}