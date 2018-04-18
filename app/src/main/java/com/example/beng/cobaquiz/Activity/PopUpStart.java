package com.example.beng.cobaquiz.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.beng.cobaquiz.R;

import java.util.Arrays;
import java.util.List;

public class PopUpStart extends Activity {

    private ListView playerOptionList;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_start);
        List<Integer> listPlayerCount = Arrays.asList(1,2,3,4);
        listAdapter = new ArrayAdapter(this, R.layout.list_player_count, listPlayerCount);

        playerOptionList = (ListView) findViewById(R.id.listPlayerCount);
        playerOptionList.setAdapter(listAdapter);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int widthj  = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(widthj*.6), (int)(height*.6));
    }
}
