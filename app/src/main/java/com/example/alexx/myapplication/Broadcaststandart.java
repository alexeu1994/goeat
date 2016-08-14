package com.example.alexx.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by alex on 09.08.2016.
 */
public class Broadcaststandart extends BroadcastReceiver {

    SharedPreferences sp;

    @Override
    public void onReceive(Context context, Intent intent) {



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
       notificationManager.cancelAll();

        Notification.Builder build = new Notification.Builder(context);

        build.setContentTitle("Scheduled Notification");
        build.setContentText("ss");
        build.setSmallIcon(R.mipmap.ic_launcher);
        build.setDefaults(Notification.DEFAULT_SOUND);
        Notification not = build.build();
        notificationManager.notify(12, not);

        sp = context.getSharedPreferences("count", Context.MODE_PRIVATE);
        int z = sp.getInt("time", 0);
        AlarmManager an = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pd = PendingIntent.getBroadcast(context, 1, intent,PendingIntent.FLAG_CANCEL_CURRENT);


        an.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + z, pd);
    }
}
