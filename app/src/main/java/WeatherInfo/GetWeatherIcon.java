package WeatherInfo;

import com.example.dyq.myweatherapp.R;

/**
 * Created by dyq on 2016/11/23.
 */
public class GetWeatherIcon {

    private int iconResource;

    public void culIconResource(String imgFormWeatherInfo){

        switch (imgFormWeatherInfo){
            case "0":
                iconResource = R.drawable.zero;
                break;
            case "1":
                iconResource = R.drawable.one;
                break;
            case "2":
                iconResource = R.drawable.two;
                break;
            case "3":
                iconResource = R.drawable.three;
                break;
            case "4":
                iconResource = R.drawable.four;
                break;
            case "5":
                iconResource = R.drawable.five;
                break;
            case "6":
                iconResource = R.drawable.six;
                break;
            case "7":
                iconResource = R.drawable.seven;
                break;
            case "8":
                iconResource = R.drawable.eight;
                break;
            case "9":
                iconResource = R.drawable.nine;
                break;
            case "10":
                iconResource = R.drawable.ten;
                break;
            case "11":
                iconResource = R.drawable.eleven;
                break;
            case "12":
                iconResource = R.drawable.twelve;
                break;
            case "13":
                iconResource = R.drawable.thirteen;
                break;
            case "14":
                iconResource = R.drawable.fourteen;
                break;
            case "15":
                iconResource = R.drawable.fifteen;
                break;
            case "16":
                iconResource = R.drawable.sixteen;
                break;
            case "17":
                iconResource = R.drawable.seventeen;
                break;
            case "18":
                iconResource = R.drawable.eighteen;
                break;
            case "19":
                iconResource = R.drawable.ninteen;
                break;
            case "20":
                iconResource = R.drawable.twenty;
                break;
            case "21":
                iconResource = R.drawable.twentyone;
                break;
            case "22":
                iconResource = R.drawable.twentytwo;
                break;
            case "23":
                iconResource = R.drawable.twentythree;
                break;
            case "24":
                iconResource = R.drawable.twentyfour;
                break;
            case "25":
                iconResource = R.drawable.twentyfive;
                break;
            case "26":
                iconResource = R.drawable.twentysix;
                break;
            case "27":
                iconResource = R.drawable.twentyseven;
                break;
            case "28":
                iconResource = R.drawable.twentyeight;
                break;
            case "29":
                iconResource = R.drawable.twentynine;
                break;
            case "30":
                iconResource = R.drawable.thirty;
                break;
            case "31":
                iconResource = R.drawable.thirtyone;
                break;
            case "32":
                iconResource = R.drawable.thirtytwo;
                break;
            case "49":
                iconResource = R.drawable.fourtynine;
                break;
            case "53":
                iconResource = R.drawable.fiftythree;
                break;
            case "54":
                iconResource = R.drawable.fiftyfour;
                break;
            case "55":
                iconResource = R.drawable.fiftyfive;
                break;
            case "56":
                iconResource = R.drawable.fiftysix;
                break;
            case "57":
                iconResource = R.drawable.fiftyseven;
                break;
            case "58":
                iconResource = R.drawable.fiftyeight;
                break;
            case "99":
                iconResource = R.drawable.nintynine;
                break;
            case "301":
                iconResource = R.drawable.threehundredone;
                break;
            case "302":
                iconResource = R.drawable.threehundredtwo;
                break;
            default:
                break;
        }
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
