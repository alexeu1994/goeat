package com.example.alexx.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 02.05.2016.
 */
public class StatsActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        list =(ListView) findViewById(R.id.list22);

        final List<String[]> colorList = new LinkedList<String[]>();
        colorList.add(new String[] { "Фон", "Red" });
        colorList.add(new String[] { "Green", "the color green" });
        colorList.add(new String[] { "Blue", "the color blue" });

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
            try {


            if (position == 0) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StatsActivity.this);
                LayoutInflater li = LayoutInflater.from(StatsActivity.this);
                View promptsView = li.inflate(R.layout.lau, null);
                builder.setView(promptsView);

                EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);



                AlertDialog alert = builder.create();
                alert.show();

                }
            }catch (Exception e){
                Toast.makeText(StatsActivity.this,e+ " ", Toast.LENGTH_LONG).show();

            }


        }
    };
    list.setOnItemClickListener(clickListener);





    }
}
