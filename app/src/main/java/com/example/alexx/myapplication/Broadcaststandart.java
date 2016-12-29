package com.example.alexx.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;

import java.util.Calendar;

/**
 * Created by alex on 09.08.2016.
 */
public class Broadcaststandart extends BroadcastReceiver {

    SharedPreferences ss;

    @Override
    public void onReceive(Context context, Intent intent) {


// выводим уведомление



        // получаем данные с файла и перезапускаем будильник
        ss = context.getSharedPreferences("count", Context.MODE_PRIVATE);

        Intent aa = new Intent("Spy");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, aa, PendingIntent.FLAG_CANCEL_CURRENT);

        int xx = ss.getInt("time", 0);
        int xx2 = ss.getInt("time2",0);
        int xx3 = ss.getInt("time3",0);
        int xx4 = ss.getInt("time4",0);

        Intent notificationIntent = new Intent(context, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification.Builder build = new Notification.Builder(context);


        build.setContentTitle(ss.getString("texttitle",context.getString(R.string.texttitle)));
        build.setContentText(ss.getString("text",context.getString(R.string.text)));
        build.setSmallIcon(R.mipmap.ic_launcher);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        build.setSound(Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.music));
        build.setDefaults(Notification.DEFAULT_VIBRATE);
        build.setContentIntent(contentIntent);
        build.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

       
        Notification not = build.build();
        notificationManager.notify(12, not);


        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int minute= cal.get(Calendar.MINUTE);





        int minutehour = h * 60 + minute;
        long one = xx * 60 + xx2;
        long futureInMillis = System.currentTimeMillis() + one * 60 * 1000;

        // удаляем ранее сохраненый будильник
        alarmManager.cancel(pendingIntent);
        long zz = (long) (xx3 * 60 - minutehour) * 60 * 1000;
        int count = (24 * 60 - minutehour + xx3 * 60) * 60 * 1000;
        long zz22 = (long) count;

        int z = (minutehour + xx * 60 + xx2) / 60;


        if (xx4 > xx3) {
            if (z >= xx3 && z < xx4) {

                alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            }
            else if (z >= xx3 && z >= xx4) {


                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz22, pendingIntent);
            } else {

                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz, pendingIntent);

            }


        }
        if (xx4 < xx3) {

            if (z >= xx3 || z <= xx4) {

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