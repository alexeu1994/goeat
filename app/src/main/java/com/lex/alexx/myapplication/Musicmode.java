package com.lex.alexx.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by alex on 17.04.2017.
 */
public class Musicmode extends AppCompatActivity {
ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicmode);

        String[] namemusic = {getString(R.string.statsmusicdefolut),getString(R.string.statsmusic1)};

        listView = (ListView) findViewById(R.id.listmusic);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.musicfon,R.id.textmusic,namemusic);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                SharedPreferences sp = getSharedPreferences("count",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();


                if(position==0) {

                    ed.putString("namemusic",getString(R.string.statsmusicdefolut));
                    startActivity(new Intent(Musicmode.this,StatsActivity.class));
                    finish();
                }

                if(position==1) {

                    ed.putString("namemusic",getString(R.string.statsmusic1));
                    startActivity(new Intent(Musicmode.this,StatsActivity.class));
                    finish();
                }
                ed.apply();
            }
        });
    }


}

