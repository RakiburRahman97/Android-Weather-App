package com.ksoft.rrkhan.androidweatherapp.Common

import android.location.Location
import java.text.SimpleDateFormat
import java.util.*

object Common {
    const val APP_ID = "3b9a822a4662d6666f03055a806e9932"
    @JvmField
    var current_location: Location? = null
    @JvmStatic
    fun convertUnixToHour(dt: Long): String {
        val date = Date(dt * 1000L)
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(date)
    }

    @JvmStatic
    fun convertUnixToDate(dt: Long): String {
        val date = Date(dt * 1000L)
        val sdf = SimpleDateFormat("hh:mm aa   EEE MMMM dd, yyyy")
        return sdf.format(date)
    }
}