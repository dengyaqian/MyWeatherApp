package com.example.dyq.myweatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import WeatherInfo.DailyData;
import WeatherInfo.GetWeatherInfo;
import WeatherInfo.HourlyData;

public class MainActivity extends Activity implements View.OnClickListener {

    GridView gridView;
    List<HourlyData> cityItemList = new ArrayList<>();
    List<DailyData> dailyItemList = new ArrayList<>();
    String fileName = "result.txt";
    String response;
    GetWeatherInfo getWeatherInfo;

    Button btSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btSettings = (Button) findViewById(R.id.btSettings);
        btSettings.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 11) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads  ().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        getHttpData();
        gridView = (GridView) findViewById(R.id.grid_view);
        setHourlyData();
        setDailyData();
        setGridView();

        setCityName();
        setCurrentTemp();
        setWeatherType();
        setWeatherQulity();
        setToday();
        setTodayLowTmp();
        setTodayHighTmp();
        setDaily();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSettings:
                Intent intent = new Intent(MainActivity.this,CityListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    private void getHttpData(){
        //请求网络数据
        getWeatherInfo = new GetWeatherInfo();
        getWeatherInfo.setInputCityName("深圳");
        getWeatherInfo.sendRequestWithHttpClient();
        response = getWeatherInfo.getResponse();
        File fileCache = this.getFilesDir();
        try{
            File dir = new File(fileCache.toString());
            if(!dir.exists()) {
                dir.mkdirs();
            }
            File txt = new File(dir.getPath(),fileName);
            FileOutputStream out = new FileOutputStream(txt);
            byte [] bytes = response.getBytes();
            out.write(bytes);
            out.close();
            getWeatherInfo.readWeatherDataFromBuffer(txt.getAbsolutePath());
            /*InputStream inputStream = getResources().openRawResource(R.raw.data);
            getWeatherInfo.readWeatherDataFromRaw(inputStream);*/
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setCityName(){

        TextView textCityName = (TextView) findViewById(R.id.cityname_view);
        textCityName.setText(getWeatherInfo.getCityName());
    }

    private void setCurrentTemp(){
        TextView temp_view = (TextView) findViewById(R.id.temp_view);
        temp_view.setText(getWeatherInfo.getCurrentTemp() + "℃");
    }

    private void setWeatherType(){
        TextView weatherType_view = (TextView) findViewById(R.id.weatherType_view);
        weatherType_view.setText(getWeatherInfo.getWeatherType());
    }

    private void setWeatherQulity(){
        TextView weatherQulity_view = (TextView) findViewById(R.id.weatherQuality_view);
        weatherQulity_view.setText("|空气" + getWeatherInfo.getQuality());
    }

    private void setToday(){
        TextView today_view = (TextView) findViewById(R.id.today_view);
        today_view.setText(getWeatherInfo.getWeek() + "  今天");
    }

    private void setTodayLowTmp(){
        TextView todayLowTmp_view = (TextView) findViewById(R.id.todayLowTemp_view);
        todayLowTmp_view.setText(getWeatherInfo.getTempLow() + "℃");
    }

    private void setTodayHighTmp(){
        TextView todayHighTmp_view = (TextView) findViewById(R.id.todayHighTemp_view);
        todayHighTmp_view.setText(getWeatherInfo.getTempHigh() + "℃");
    }

    private void setDaily(){
        TextView week = (TextView) findViewById(R.id.week1);
        ImageView img = (ImageView) findViewById(R.id.img1);
        TextView tempLow = (TextView) findViewById(R.id.tempLow1);
        TextView tempHigh = (TextView) findViewById(R.id.tempHigh1);
        week.setText(dailyItemList.get(1).getWeek());
        img.setImageResource(dailyItemList.get(1).getWeatherImg());
        tempLow.setText(dailyItemList.get(1).getTempLow() + "℃~");
        tempHigh.setText(dailyItemList.get(1).getTempHigh() + "℃");

        week = (TextView) findViewById(R.id.week2);
        img = (ImageView) findViewById(R.id.img2);
        tempLow = (TextView) findViewById(R.id.tempLow2);
        tempHigh = (TextView) findViewById(R.id.tempHigh2);
        week.setText(dailyItemList.get(2).getWeek());
        img.setImageResource(dailyItemList.get(2).getWeatherImg());
        tempLow.setText(dailyItemList.get(2).getTempLow() + "℃~");
        tempHigh.setText(dailyItemList.get(2).getTempHigh() + "℃");

        week = (TextView) findViewById(R.id.week3);
        img = (ImageView) findViewById(R.id.img3);
        tempLow = (TextView) findViewById(R.id.tempLow3);
        tempHigh = (TextView) findViewById(R.id.tempHigh3);
        week.setText(dailyItemList.get(3).getWeek());
        img.setImageResource(dailyItemList.get(3).getWeatherImg());
        tempLow.setText(dailyItemList.get(3).getTempLow() + "℃~");
        tempHigh.setText(dailyItemList.get(3).getTempHigh() + "℃");

        week = (TextView) findViewById(R.id.week4);
        img = (ImageView) findViewById(R.id.img4);
        tempLow = (TextView) findViewById(R.id.tempLow4);
        tempHigh = (TextView) findViewById(R.id.tempHigh4);
        week.setText(dailyItemList.get(4).getWeek());
        img.setImageResource(dailyItemList.get(4).getWeatherImg());
        tempLow.setText(dailyItemList.get(4).getTempLow() + "℃~");
        tempHigh.setText(dailyItemList.get(4).getTempHigh() + "℃");

        week = (TextView) findViewById(R.id.week5);
        img = (ImageView) findViewById(R.id.img5);
        tempLow = (TextView) findViewById(R.id.tempLow5);
        tempHigh = (TextView) findViewById(R.id.tempHigh5);
        week.setText(dailyItemList.get(5).getWeek());
        img.setImageResource(dailyItemList.get(5).getWeatherImg());
        tempLow.setText(dailyItemList.get(5).getTempLow() + "℃~");
        tempHigh.setText(dailyItemList.get(5).getTempHigh() + "℃");

        week = (TextView) findViewById(R.id.week6);
        img = (ImageView) findViewById(R.id.img6);
        tempLow = (TextView) findViewById(R.id.tempLow6);
        tempHigh = (TextView) findViewById(R.id.tempHigh6);
        week.setText(dailyItemList.get(6).getWeek());
        img.setImageResource(dailyItemList.get(6).getWeatherImg());
        tempLow.setText(dailyItemList.get(6).getTempLow() + "℃~");
        tempHigh.setText(dailyItemList.get(6).getTempHigh() + "℃");

    }

    private void setHourlyData(){
        cityItemList = getWeatherInfo.getHourlyDataList();
    }

    private void setDailyData(){
        dailyItemList = getWeatherInfo.getDailyDataList();
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
            tempView.setText(city.getCityTemp() + "℃");
            return convertView;
        }
    }
}


