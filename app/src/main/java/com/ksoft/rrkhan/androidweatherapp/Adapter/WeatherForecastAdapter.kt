package com.ksoft.rrkhan.androidweatherapp.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ksoft.rrkhan.androidweatherapp.Adapter.WeatherForecastAdapter.MyViewHolder
import com.ksoft.rrkhan.androidweatherapp.R

abstract class WeatherForecastAdapter : RecyclerView.Adapter<MyViewHolder?>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_dste: TextView? = null
        var txt_description: TextView? = null
        var txt_temperature: TextView? = null
        var img_weather: ImageView

        init {
            img_weather = itemView.findViewById<View>(R.id.img_weather) as ImageView
        }
    }
}