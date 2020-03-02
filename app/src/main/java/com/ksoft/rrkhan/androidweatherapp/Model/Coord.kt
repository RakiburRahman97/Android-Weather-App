package com.ksoft.rrkhan.androidweatherapp.Model

class Coord {
    var lon = 0.0
    var lat = 0.0

    override fun toString(): String {
        return StringBuilder("[").append(lat).append(",").append(lon).append("]").toString()
    }
}