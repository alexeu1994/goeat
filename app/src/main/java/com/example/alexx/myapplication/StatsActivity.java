package com.example.alexx.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 02.05.2016.
 */
public class StatsActivity extends AppCompatActivity {
    ListView list;
Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        list =(ListView) findViewById(R.id.list22);




        final List<String[]> colorList = new LinkedList<String[]>();
        colorList.add(new String[] { getString(R.string.statstext), "" });
        colorList.add(new String[] { getString(R.string.statstextfeedback), getString(R.string.statstextfeedbackdown) });
       // colorList.add(new String[] { "Музыка", "" });

        // Note - we're specifying android.R.id.text1 as a param, but it's ignored
        // because we override getView(). That param usually tells ArrayAdapter
        // where to find the one TextView entity in a complex layout.
        // If our layout was a simple TextView (like android.R.layout.simple_list_item_1),
        // we wouldn't need that param.

        list.setAdapter(new ArrayAdapter<String[]>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                colorList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // Must always return just a View.
                View view = super.getView(position, convertView, parent);

                // If you look at the android.R.layout.simple_list_item_2 source, you'll see
                // it's a TwoLineListItem with 2 TextViews - text1 and text2.
                //TwoLineListItem listItem = (TwoLineListItem) view;
                String[] entry = colorList.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        });



    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                long id) {



            if (position == 0) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StatsActivity.this,R.style.AppTheme);
                LayoutInflater li = LayoutInflater.from(StatsActivity.this);
                View promptsView = li.inflate(R.layout.lau, null);
                builder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                final EditText userInput2=(EditText) promptsView.findViewById(R.id.edittextDialog2);
                SharedPreferences spe = getSharedPreferences("count",MODE_PRIVATE);
                userInput.setText(spe.getString("texttitle", getResources().getString(R.string.texttitle)));
                userInput2.setText(spe.getString("text",getResources().getString(R.string.text)));


 builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
     @Override
     public void onClick(DialogInterface dialogInterface, int i) {
         SharedPreferences sp = getSharedPreferences("count",MODE_PRIVATE);
         SharedPreferences.Editor ed = sp.edit();
         ed.putString("texttitle",userInput.getText().toString());
         ed.putString("text",userInput2.getText().toString());
         ed.apply();
         dialogInterface.cancel();
     }
 }
 );






                AlertDialog alert = builder.create();
                alert.show();

                }
            if (position == 1) {

                final String appPackageName = getApplicationContext().getPackageName(); //
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }
           if (position == 2) {

            }



        }
    };
    list.setOnItemClickListener(clickListener);





    }
}
