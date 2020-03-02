package com.ksoft.rrkhan.androidweatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.ksoft.rrkhan.androidweatherapp.Common.Common
import com.ksoft.rrkhan.androidweatherapp.Common.Common.convertUnixToDate
import com.ksoft.rrkhan.androidweatherapp.Common.Common.convertUnixToHour
import com.ksoft.rrkhan.androidweatherapp.Model.WeatherResult
import com.ksoft.rrkhan.androidweatherapp.Retrofit.IOpenWeatherMap
import com.ksoft.rrkhan.androidweatherapp.Retrofit.RetrofitClient
import com.squareup.picasso.Picasso
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder
import kotlin.text.StringBuilder

/**
 * A simple [Fragment] subclass.
 */
class TodayWeatherFragment : Fragment() {
    var img_weather: ImageView? = null
    var txt_city_name: TextView? = null
    var txt_humidity: TextView? = null
    var txt_sunrise: TextView? = null
    var txt_sunset: TextView? = null
    var txt_pressure: TextView? = null
    var txt_temperature: TextView? = null
    var txt_description: TextView? = null
    var txt_date_time: TextView? = null
    var txt_wind: TextView? = null
    var txt_geo_coord: TextView? = null
    var weather_panel: LinearLayout? = null
    var loading: ProgressBar? = null
    var compositeDisposable: CompositeDisposable
    var mService: IOpenWeatherMap
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_today_weather, container, false)
        img_weather = itemView.findViewById<View>(R.id.img_weather) as ImageView
        txt_city_name = itemView.findViewById<View>(R.id.txt_city_name) as TextView
        txt_date_time = itemView.findViewById<View>(R.id.txt_date_time) as TextView
        txt_description = itemView.findViewById<View>(R.id.txt_description) as TextView
        txt_humidity = itemView.findViewById<View>(R.id.txt_humidity) as TextView
        txt_sunrise = itemView.findViewById<View>(R.id.txt_sunrise) as TextView
        txt_sunset = itemView.findViewById<View>(R.id.txt_sunset) as TextView
        txt_pressure = itemView.findViewById<View>(R.id.txt_pressure) as TextView
        txt_temperature = itemView.findViewById<View>(R.id.txt_temperature) as TextView
        txt_wind = itemView.findViewById<View>(R.id.txt_wind) as TextView
        txt_geo_coord = itemView.findViewById<View>(R.id.txt_geo_coord) as TextView
        weather_panel = itemView.findViewById<View>(R.id.weather_panel) as LinearLayout
        loading = itemView.findViewById<View>(R.id.loading) as ProgressBar
        wetherInformation
        return itemView
    }

    //Load Image
    //Display Panal
    private val wetherInformation: Unit
        private get() {
            compositeDisposable.add(mService.getWeatherByLatLng(Common.current_location!!.latitude.toString(), Common.current_location!!.longitude.toString(), Common.APP_ID, "metric")!!.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(Consumer<WeatherResult> { weatherResult ->
                        //Load Image
                        Picasso.get().load(StringBuilder("https://openweathermap.org/img/w/").append(weatherResult.weather!![0].icon).append(".png").toString()).into(img_weather)
                        txt_city_name!!.text = weatherResult.name
                        txt_description!!.text = weatherResult.weather!![0].description
                        txt_wind.setText(StringBuilder(String.))
                        //txt_wind!!.setText(StringBuilder("Speed: ").append(String.valueOf(weatherResult.wind!!.speed)).append(" Deg: ").append(String.valueOf(weatherResult.wind!!.deg)).toString())
                        txt_temperature!!.setText(StringBuilder(String.valueOf(weatherResult.main!!.temp)).append("°C").toString())
                        txt_date_time!!.text = convertUnixToDate(weatherResult.dt.toLong())
                        txt_pressure!!.setText(StringBuilder(String.valueOf(weatherResult.main!!.pressure)).append(" hpa").toString())
                        txt_humidity!!.setText(StringBuilder(String.valueOf(weatherResult.main!!.humidity)).append(" %").toString())
                        txt_sunrise!!.text = convertUnixToHour(weatherResult.sys!!.sunrise.toLong())
                        txt_sunset!!.text = convertUnixToHour(weatherResult.sys!!.sunset.toLong())
                        txt_geo_coord!!.text = StringBuilder("[").append(weatherResult.coord.toString()).append("]").toString()
                        //Display Panal
                        weather_panel!!.visibility = View.VISIBLE
                        loading!!.visibility = View.GONE
                    }, Consumer { throwable -> Toast.makeText(activity, "" + throwable.message, Toast.LENGTH_SHORT).show() }
                    )!!)
        }

    companion object {
        private var instance: TodayWeatherFragment? = null
        fun getInstance(): TodayWeatherFragment? {
            if (instance == null) instance = TodayWeatherFragment()
            return instance
        }
    }

    init {
        compositeDisposable = CompositeDisposable()
        val retrofit = RetrofitClient.instance
        mService = retrofit!!.create(IOpenWeatherMap::class.java)
    }
}

private fun Any.subscribe(consumer: Consumer<WeatherResult>, consumer1: Consumer<Throwable>): Disposable? {

}
