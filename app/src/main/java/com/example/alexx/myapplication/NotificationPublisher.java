package com.example.alexx.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class NotificationPublisher extends BroadcastReceiver {

    SharedPreferences sp;

    public void onReceive(Context context, Intent intent) {



        sp = context.getSharedPreferences("count", 0);
        int x = sp.getInt("time", Context.MODE_PRIVATE);
        Intent intentb = new Intent("Spy");
        PendingIntent pd = PendingIntent.getBroadcast(context,1,intentb,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+x,pd);
    }
}