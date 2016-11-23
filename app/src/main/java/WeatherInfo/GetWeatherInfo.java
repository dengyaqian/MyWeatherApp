package WeatherInfo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyq on 2016/11/10.
 */
public class GetWeatherInfo {

    private String inputCityName;    //输入城市名来查询天气信息
    private String cityName;    //从网页上返回的城市名
    private String week;    //表示星期几
    private String weatherType; //多云、晴、阴...
    private String currentTemp; //当前温度
    private String tempHigh;   //当天最高温度
    private String tempLow;     //当天最低温度
    private String imgIndex;    //天气类型的图标索引号
    private String humidity;     //当前湿度
    private String pressure;    //当前气压
    private String windPower;   //当前风级
    private String updateTime;  //更新时间
    private String pm2_5;       //PM2.5值
    private String quality;     //空气质量
    private List<HourlyData> hourlyDataList;
    private List<DailyData> dailyDataList;
    private String response;

    public void init(){
        //sendRequestWithHttpClient();
        //readWeatherData();
    }

    public void readWeatherDataFromRaw(InputStream inputStream){

            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String result = "";
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        parseJsonWithJsonObject(result);
    }

    public void readWeatherDataFromBuffer(String fileName){
        String res="";
        try {
            String encoding="utf-8";
            File file=new File(fileName);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    res += lineTxt;
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        parseJsonWithJsonObject(res);
    }

    public void sendRequestWithHttpClient(){

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://api.jisuapi.com/weather/query?appkey=f9975f3333d25e6a&city=%E6%B7%B1%E5%9C%B3");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode() == 200){
                        HttpEntity httpEntity = httpResponse.getEntity();
                        response = EntityUtils.toString(httpEntity,"utf-8");
                        //parseJsonWithJsonObject(response);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();*/

        try{
            String encodeCityName = URLEncoder.encode(inputCityName,"utf-8");
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://api.jisuapi.com/weather/query?appkey=f9975f3333d25e6a&city=" + encodeCityName);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity,"utf-8");
                //parseJsonWithJsonObject(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseJsonWithJsonObject(String jsonData){
        hourlyDataList = new ArrayList<HourlyData>();
        dailyDataList = new ArrayList<DailyData>();
        try{
            JSONObject jObject = new JSONObject(jsonData);
            JSONObject jsonObject = jObject.getJSONObject("result");
            cityName = jsonObject.getString("city");
            week = jsonObject.getString("week");
            weatherType = jsonObject.getString("weather");
            currentTemp = jsonObject.getString("temp");
            tempHigh = jsonObject.getString("temphigh");
            tempLow = jsonObject.getString("templow");
            imgIndex = jsonObject.getString("img");
            humidity = jsonObject.getString("humidity");
            pressure = jsonObject.getString("pressure");
            windPower = jsonObject.getString("windpower");
            updateTime = jsonObject.getString("updatetime");

            JSONObject jObjectAqi = jsonObject.getJSONObject("aqi");
            pm2_5 = jObjectAqi.getString("ipm2_5");
            quality = jObjectAqi.getString("quality");

            JSONArray jsonArray = jsonObject.getJSONArray("hourly");

            HourlyData hourlyData;
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jObjectHourly = jsonArray.getJSONObject(i);
                hourlyData = new HourlyData();
                hourlyData.setCityTime(jObjectHourly.getString("time"));
                hourlyData.setCityTemp(jObjectHourly.getString("temp"));
                hourlyData.setweatherImg(jObjectHourly.getString("img"));
                hourlyDataList.add(hourlyData);
            }

            DailyData dailyData;
            jsonArray = jsonObject.getJSONArray("daily");
            for(int j = 0 ; j < jsonArray.length(); j++){
                JSONObject jObjectDaily = jsonArray.getJSONObject(j);
                dailyData = new DailyData();
                dailyData.setWeek(jObjectDaily.getString("week"));
                JSONObject jObjectNight = jObjectDaily.getJSONObject("night");
                dailyData.setTempLow(jObjectNight.getString("templow"));
                JSONObject jObjectDay = jObjectDaily.getJSONObject("day");
                dailyData.setTempHigh(jObjectDay.getString("temphigh"));
                dailyData.setweatherImg(jObjectDay.getString("img"));
                dailyDataList.add(dailyData);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getInputCityName() {
        return inputCityName;
    }

    public void setInputCityName(String inputCityName) {
        this.inputCityName = inputCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getImgIndex() {
        return imgIndex;
    }

    public void setImgIndex(String imgIndex) {
        this.imgIndex = imgIndex;
    }

    public String getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh;
    }

    public String getTempLow() {
        return tempLow;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWindpower() {
        return windPower;
    }

    public void setWindpower(String windPower) {
        this.windPower = windPower;
    }

    public List<HourlyData> getHourlyDataList() {
        if(!hourlyDataList.isEmpty()){
            System.out.println("hourlyDataList不为空");
        }
        return hourlyDataList;
    }

    public void setHourlyDataList(List<HourlyData> hourlyDataList) {
        this.hourlyDataList = hourlyDataList;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public List<DailyData> getDailyDataList() {
        return dailyDataList;
    }

    public void setDailyDataList(List<DailyData> dailyDataList) {
        this.dailyDataList = dailyDataList;
    }
}
