package com.lex.alexx.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

public class NotificationPublisher extends BroadcastReceiver {

    SharedPreferences ss;

    public void onReceive(Context context, Intent intent) {


// после перезагрузки сервис умер, получаем данные из файла
// создаем нов сервис и кидаем в другой ресивер

        // почему так?, чтобы не выводилось первое уведомление!
        ss = context.getSharedPreferences("count", Context.MODE_PRIVATE);
        boolean b = ss.getBoolean("boolean", false);
        if (b == true) {


            Intent intentb = new Intent("Spy");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intentb, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


            int xx = ss.getInt("time", 3);
            int xx2 = ss.getInt("time2", 0);
            int xx3 = ss.getInt("time3", 8);
            int xx4 = ss.getInt("time4", 21);
            long sys = ss.getLong("timesys", 0);


            Calendar cal = Calendar.getInstance();
            int h = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);


            int minutehour = h * 60 + minute;


            long one = xx * 60 + xx2;
            long futureInMillis = System.currentTimeMillis() + one * 60 * 1000;



            long zz = (long) (xx3 * 60 - minutehour) * 60 * 1000;
            int count = (24 * 60 - minutehour + xx3 * 60) * 60 * 1000;
            long zz22 = (long) count;

            int z = (minutehour + xx * 60 + xx2) / 60;

            long timedorestart = sys + one * 60 * 1000;

/* Аццкий велосипед который проверяет если будильник поподает в интервал времени, при перезагрузке
            или при выкл вкл кнопкой, будильник прозвинит раньше, если время постановленое будильника в моде
              еще не прошло. т.е. при перезагрузки будильник прозвенит через оставшееся время, которое было до перезагрузки */

            if (System.currentTimeMillis() < timedorestart && xx4>xx3 && z >= xx3 && z <xx4||
                    System.currentTimeMillis() < timedorestart && xx4 < xx3 && z >= xx3 || System.currentTimeMillis() < timedorestart &&xx4 < xx3 && z <= xx4) {

                long t = timedorestart - System.currentTimeMillis();

                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + t, pendingIntent);
            } else {

                if (xx4 > xx3) {
                    if (z >= xx3 && z <xx4) {

                        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
                    } else if (z >= xx3 && z >= xx4) {


                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz22, pendingIntent);
                    } else {

                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz, pendingIntent);

                    }


                }
                if (xx4 < xx3) {

                    if (z >= xx3 || z <= xx4) {

                        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);

                    } else {
                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + zz, pendingIntent);

                    }

                }
                if (xx4 == xx3) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);

                }


            }


        }
    }
}