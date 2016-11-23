package WeatherInfo;

/**
 * Created by dyq on 2016/11/21.
 */
public class DailyData {

    private String week;

    private String img;

    private String tempLow;

    private String tempHigh;

    private int weatherImg;

    private GetWeatherIcon getWeatherIcon;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getWeatherImg() {
        return weatherImg;
    }

    public void setweatherImg(String weatherImgIndex) {
        getWeatherIcon = new GetWeatherIcon();
        getWeatherIcon.culIconResource(weatherImgIndex);
        this.weatherImg = getWeatherIcon.getIconResource();
    }

}
