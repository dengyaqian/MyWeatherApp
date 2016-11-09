package com.example.dyq.myweatherapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    List<CityItem> cityItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        gridView = (GridView) findViewById(R.id.grid_view);
        setData();
        setGridView();
    }

    private void setData(){

        cityItemList = new ArrayList<CityItem>();
        CityItem cityItem = new CityItem();
        cityItem.setCityTime("1:00");
        cityItem.setCityTemp("26℃");
        cityItemList.add(cityItem);

        cityItem = new CityItem();
        cityItem.setCityTime("2:00");
        cityItem.setCityTemp("26℃");
        cityItemList.add(cityItem);

        cityItem = new CityItem();
        cityItem.setCityTime("3:00");
        cityItem.setCityTemp("26℃");
        cityItemList.add(cityItem);

        cityItem = new CityItem();
        cityItem.setCityTime("4:00");
        cityItem.setCityTemp("26℃");
        cityItemList.add(cityItem);

        cityItemList.addAll(cityItemList);
    }

    private void setGridView(){

        int size = cityItemList.size();
        int length = 100;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = displayMetrics.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth,
                ViewGroup.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),cityItemList);
        gridView.setAdapter(adapter);
    }

    public class GridViewAdapter extends BaseAdapter{

        Context context;
        List<CityItem> list;

        public GridViewAdapter(Context _context,List<CityItem> _list){
            this.context = _context;
            this.list = _list;
        }

        @Override
        public int getCount() {
            return cityItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return cityItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_item,null);
            TextView timeView = (TextView) convertView.findViewById(R.id.time_view);
            TextView tempView = (TextView) convertView.findViewById(R.id.temp_view);
            CityItem city = list.get(position);

            timeView.setText(city.getCityTime());
            tempView.setText(city.getCityTemp());
            return convertView;
        }
    }

    public class CityItem{

        String cityTime;
        String cityTemp;

        public String getCityTime() {
            return cityTime;
        }

        public void setCityTime(String cityName) {
            this.cityTime = cityName;
        }

        public String getCityTemp() {
            return cityTemp;
        }

        public void setCityTemp(String cityTemp) {
            this.cityTemp = cityTemp;
        }
    }
}


