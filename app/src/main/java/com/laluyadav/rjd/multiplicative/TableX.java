package com.laluyadav.rjd.multiplicative;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laluyadav.rjd.R;

import java.util.ArrayList;

public class TableX extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seek_baar);
        textView = findViewById(R.id.text_multi);
        listView = findViewById(R.id.list_multi);
        seekBar = findViewById(R.id.seekbaar);

        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(TableX.this,"the progress bar is "+progress,Toast.LENGTH_LONG).show();
                populate(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void populate(int table){
        ArrayList<String> multTable = new ArrayList<>();
        for(int i = 0;i<10;i++){
            multTable.add(table+"X"+i+"="+table*i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1,multTable);
        listView.setAdapter(arrayAdapter);
    }
}
