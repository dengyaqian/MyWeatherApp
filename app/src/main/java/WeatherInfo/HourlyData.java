package WeatherInfo;

/**
 * Created by dyq on 2016/11/10.
 */
public class HourlyData {

    private String cityTime;

    private String cityTemp;

    private int weatherImg;

    private GetWeatherIcon getWeatherIcon;

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
        getWeatherIcon = new GetWeatherIcon();
        getWeatherIcon.culIconResource(weatherImgIndex);
        this.weatherImg = getWeatherIcon.getIconResource();
    }
}
