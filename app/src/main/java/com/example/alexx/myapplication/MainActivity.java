package com.example.alexx.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Кнопки
    Button onOff;
    Button b2;
    Button b3;
    Button b4;
    PendingIntent pd;

    //Стартует Активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //выбераем лайаут

        setContentView(R.layout.main);

//Инициализируем кнопочки

        onOff = (Button) findViewById(R.id.buttonn);

        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);


        //Создаем  слушатель
        View.OnClickListener a = new View.OnClickListener() {

            //Переопределяем метод отлавливающий события
            @Override
            public void onClick(View view) {

                //Если смобытие ровно кнопке 2
                //откроется новое Активити
                if (view.getId() == R.id.button3) {
                    b3.setText("1");
                    //Метод выова картинок активити и тп
                    // (текущее активити.this , активити которое нужно открыть)
                    Intent as = new Intent(MainActivity.this, StatsActivity.class);
                    startActivity(as);
                }

                if (view.getId() == R.id.button4) {
                    Intent diary = new Intent(MainActivity.this, DiaryActivity.class);
                    startActivity(diary);
                }

                if (view.getId() == R.id.button2) {
                    Intent mode = new Intent(MainActivity.this, ModeActivity.class);
                    startActivity(mode);
                }
                if (view.getId() == R.id.buttonn) {
                    Intent intent = new Intent("Spy");
                    pd = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    pd.cancel();
                    NotificationManager mm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mm.cancelAll();
                    SharedPreferences ss = getSharedPreferences("count",MODE_PRIVATE);
                    SharedPreferences.Editor ed=ss.edit();
                    ed.putBoolean("boolean",false);
                    ed.apply();

                }


            }
        };

        /* вешаем на кнопку слушатель
           в класе button есть метод setOnclickListener()
           куда мы и передаем объект слушатель*/
        b2.setOnClickListener(a);
        b3.setOnClickListener(a);
        b4.setOnClickListener(a);
        onOff.setOnClickListener(a);
    }


}