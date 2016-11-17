package com.example.dyq.myweatherapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import WeatherInfo.GetWeatherInfo;
import WeatherInfo.HourlyData;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    List<HourlyData> cityItemList = new ArrayList<>();
    String fileName = "result.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 11) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads  ().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        gridView = (GridView) findViewById(R.id.grid_view);
        setData();
        setGridView();
    }

    private void setData(){

        File fileCache = this.getFilesDir();
        GetWeatherInfo getWeatherInfo = new GetWeatherInfo();
        getWeatherInfo.sendRequestWithHttpClient();
        String response = getWeatherInfo.getResponse();
        try{
            File dir = new File(fileCache.toString());
            if(!dir.exists()) {
                dir.mkdirs();
            }
            File txt = new File(dir.getPath(),fileName);
            FileOutputStream out = new FileOutputStream(txt);
            /*FileOutputStream fout =openFileOutput(fileName, MODE_PRIVATE);*/
            byte [] bytes = response.getBytes();
            out.write(bytes);
            out.close();
            getWeatherInfo.readWeatherDataFromBuffer(txt.getAbsolutePath());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*InputStream inputStream = getResources().openRawResource(R.raw.data);
        getWeatherInfo.readWeatherDataFromRaw(inputStream);*/


        cityItemList = getWeatherInfo.getHourlyDataList();
    }

    private void setGridView(){
        if(!cityItemList.isEmpty()){
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
            gridView.setHorizontalSpacing(3); // 设置列表项水平间距
            gridView.setStretchMode(GridView.NO_STRETCH);
            gridView.setNumColumns(size); // 设置列数量=列表集合数

            GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),cityItemList);
            gridView.setAdapter(adapter);
        }
        else {
            System.out.println("cityItemData为空");
        }

    }

    public class GridViewAdapter extends BaseAdapter{

        Context context;
        List<HourlyData> list;

        public GridViewAdapter(Context _context,List<HourlyData> _list){
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
            ImageView imageView = (ImageView) convertView.findViewById(R.id.img_view);
            TextView tempView = (TextView) convertView.findViewById(R.id.temp_view);
            HourlyData city = list.get(position);

            timeView.setText(city.getCityTime());
            imageView.setImageResource(city.getweatherImg());
            tempView.setText(city.getCityTemp());
            return convertView;
        }
    }
}


