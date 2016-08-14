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

/**
 * Created by alex on 31.07.2016.
 */
public class ModeActivity extends AppCompatActivity {

    Button save;
    EditText one;

    int timeone;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode);
        save = (Button) findViewById(R.id.button);
        one = (EditText) findViewById(R.id.editText);

    }


    public void Iss(View view) {

        String onetxt = one.getText().toString();
        timeone = Integer.parseInt(onetxt)*1000;
        sp=getSharedPreferences("count",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
       ed.putInt("time",timeone );
        ed.apply();
        scheduleNotification();
    }



    public void scheduleNotification() {

        NotificationManager nn = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
      nn.cancelAll();

        SharedPreferences ss = getSharedPreferences("count",MODE_PRIVATE);
        int xx =ss.getInt("time",0);


        Intent notificationIntent = new Intent("Spy");

        //Используется метод putExtra. Он имеет множество вариаций и аналогичен методу put
        // для Map, т.е. добавляет к объекту пару. Первый параметр – это ключ(имя), второй - значение.
         PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = System.currentTimeMillis() + xx;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
alarmManager.cancel(pendingIntent);

        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }


}