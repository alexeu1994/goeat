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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    // Кнопки
    Button onOff;
    Button b2;
    Button b3;
TextView text5;
    PendingIntent pd;

    //Стартует Активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //выбераем лайаут

        setContentView(R.layout.main);

//Инициализируем кнопочки

        onOff = (Button) findViewById(R.id.buttonn);
onoffmode();
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);


        //Создаем  слушатель
        View.OnClickListener a = new View.OnClickListener() {

            //Переопределяем метод отлавливающий события
            @Override
            public void onClick(View view) {

                //Если смобытие ровно кнопке 2
                //откроется новое Активити
                if (view.getId() == R.id.button3) {

                    //Метод выова картинок активити и тп
                    // (текущее активити.this , активити которое нужно открыть)
                    Intent as = new Intent(MainActivity.this, StatsActivity.class);
                    startActivity(as);
                }



                if (view.getId() == R.id.button2) {
                    Intent mode = new Intent(MainActivity.this, ModeActivity.class);
                    startActivity(mode);
                }



            }
        };

        /* вешаем на кнопку слушатель
           в класе button есть метод setOnclickListener()
           куда мы и передаем объект слушатель*/
        b2.setOnClickListener(a);
        b3.setOnClickListener(a);






    }

    @Override
    protected void onResume() {
        super.onResume();
        text5 = (TextView)findViewById(R.id.textView5);

        SharedPreferences shar = getSharedPreferences("count",MODE_PRIVATE);
        int time = shar.getInt("time",0);
        int time2 =   shar.getInt("time2",0);
        int time3 =    shar.getInt("time3",0);
        int time4 =    shar.getInt("time4",0);

        text5.setText(getString(R.string.mainothertext)+ time + " ч " + time2+" м c " +time3+ " ч до " +time4+" ч"  );


        onoffmode();
    }



    public void onoffmode(){
    SharedPreferences as = getSharedPreferences("count",MODE_PRIVATE);
    if (as.getBoolean("boolean",true)==true)
    {onOff.setBackgroundResource(R.drawable.buttonon);}
    else{onOff.setBackgroundResource(R.drawable.buttonof);}}

    public void dsf(View view) {
        SharedPreferences ss = getSharedPreferences("count",MODE_PRIVATE);

       Boolean b= ss.getBoolean("boolean",true);
            if (b==false){
                onOff.setBackgroundResource(R.drawable.buttonon);

                SharedPreferences.Editor ed=ss.edit();
                ed.putBoolean("boolean",true);
                Toast.makeText(getApplicationContext(),"on",Toast.LENGTH_SHORT).show();

              //  long sys = System.currentTimeMillis();
               // ed.putLong("timesys",sys);
                ed.apply();
                Intent intentb = new Intent("onof");
                sendBroadcast(intentb);


            }


            else
            {
                Toast.makeText(getApplicationContext(),"off",Toast.LENGTH_SHORT).show();
                onOff.setBackgroundResource(R.drawable.buttonof);
                Intent intent = new Intent("Spy");
                pd = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                pd.cancel();
                NotificationManager mm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mm.cancelAll();
                SharedPreferences.Editor ed=ss.edit();
                ed.putBoolean("boolean",false);
                ed.putLong("timesys",System.currentTimeMillis());
                ed.apply();
            }
        }
    }
