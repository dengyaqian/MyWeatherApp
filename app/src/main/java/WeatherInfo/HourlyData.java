package WeatherInfo;

import com.example.dyq.myweatherapp.R;

/**
 * Created by dyq on 2016/11/10.
 */
public class HourlyData {
    String cityTime;
    String cityTemp;
    int weatherImg;

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

    public int getweatherImg() {
        return weatherImg;
    }

    public void setweatherImg(String weatherImgIndex) {
        this.weatherImg = culWeatherImg(weatherImgIndex);
    }

    public int culWeatherImg(String weatherImgIndex){
        int tmp = 0;
        if(weatherImgIndex.equals("0")){
            tmp = R.drawable.qing;
        }
        else if(weatherImgIndex.equals("1")){
            tmp = R.drawable.duoyun;
        }
        else if(weatherImgIndex.equals("2")){
            tmp = R.drawable.yin;
        }
        else if(weatherImgIndex.equals("3")){
            tmp = R.drawable.yin;
        }
        else if(weatherImgIndex.equals("4")){
            tmp = R.drawable.yin;
        }
        else if(weatherImgIndex.equals("5")){
            tmp = R.drawable.yin;
        }
        else if(weatherImgIndex.equals("6")){
            tmp = R.drawable.yin;
        }
        return tmp;

    }
}
