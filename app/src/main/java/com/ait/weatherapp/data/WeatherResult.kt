package com.ait.weatherapp.data

class WeatherResult(val image : String?, val temp: Number?, val description: String?, val temp_min: Number?, val temp_max: Number?, val humidity: Number?, val feels_like: Number?, val weather: String?)

// result generated from /json

data class Base(val coord: Coord?, val weather: List<Weather>?, val base: String?, val main: Main?, val visibility: Number?, val wind: Wind?, val snow: Snow?, val clouds: Clouds?, val dt: Number?, val sys: Sys?, val timezone: Number?, val id: Number?, val name: String?, val cod: Number?)

data class Clouds(val all: Number?)

data class Coord(val lon: Number?, val lat: Number?)

data class Main(val temp: Number?, val feels_like: Number?, val temp_min: Number?, val temp_max: Number?, val pressure: Number?, val humidity: Number?)

data class Snow(val perHour: Number?)

data class Sys(val type: Number?, val id: Number?, val country: String?, val sunrise: Number?, val sunset: Number?)

data class Weather(val id: Number?, val main: String?, val description: String?, val icon: String?)

data class Wind(val speed: Number?, val deg: Number?, val gust: Number?)