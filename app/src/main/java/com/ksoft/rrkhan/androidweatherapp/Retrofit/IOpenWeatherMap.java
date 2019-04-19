package com.ksoft.rrkhan.androidweatherapp.Retrofit;

import com.ksoft.rrkhan.androidweatherapp.Model.WeatherForecastResult;
import com.ksoft.rrkhan.androidweatherapp.Model.WeatherResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {
    @GET("weather")
    Observable<WeatherResult>getWeatherByLatLng(@Query("lat") String lat, @Query("lon") String lng, @Query("appid") String appid, @Query("units") String unit);

    @GET("forecast")
    Observable<WeatherForecastResult>getWeatherForecastByLatLng(@Query("lat") String lat, @Query("lon") String lng, @Query("appid") String appid, @Query("units") String unit);
}
