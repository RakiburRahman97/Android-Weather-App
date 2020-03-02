package com.ksoft.rrkhan.androidweatherapp.Retrofit

import com.ksoft.rrkhan.androidweatherapp.Model.WeatherForecastResult
import com.ksoft.rrkhan.androidweatherapp.Model.WeatherResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenWeatherMap {
    @GET("weather")
    fun getWeatherByLatLng(@Query("lat") lat: String?, @Query("lon") lng: String?, @Query("appid") appid: String?, @Query("units") unit: String?): Observable<WeatherResult?>?

    @GET("forecast")
    fun getWeatherForecastByLatLng(@Query("lat") lat: String?, @Query("lon") lng: String?, @Query("appid") appid: String?, @Query("units") unit: String?): Observable<WeatherForecastResult?>?
}