package com.example.dyq.myweatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by dyq on 2016/11/23.
 */
public class CityListActivity extends Activity implements View.OnClickListener {

    private static final String[] strs = new String[]{
            "first","second","third"
    };

    private ListView cityListView;

    Button btReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.city_list);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar);
        btReturn = (Button) findViewById(R.id.bt_return);
        btReturn.setOnClickListener(this);

        cityListView = (ListView) findViewById(R.id.cityListView);
        cityListView.setAdapter(new ArrayAdapter<String>(this,R.layout.city_item,strs));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_return:
                Intent intent = new Intent(CityListActivity.this,MainActivity.class);
                startActivity(intent);
        }
    }
}
