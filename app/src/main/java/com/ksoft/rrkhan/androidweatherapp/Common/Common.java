package com.ksoft.rrkhan.androidweatherapp.Common;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String APP_ID = "3b9a822a4662d6666f03055a806e9932";

    public static Location current_location = null;
    public static String convertUnixToHour(long dt){
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }

    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa   EEE MMMM dd, yyyy");
        String formatted = sdf.format(date);
        return formatted;
    }
}
