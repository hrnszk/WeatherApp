package com.ait.weatherapp.retrofit

import com.ait.weatherapp.data.Base
import com.ait.weatherapp.data.City
import com.ait.weatherapp.data.WeatherResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//link: https://api.openweathermap.org/data/2.5/weather?q=Budapest&units=metric&appid=428921c522a297791f09b79640e27934
//host: https://api.openweathermap.org
//path: /data/2.5/weather?
//Query params: q=Budapest,hu&units=metric&appid=428921c522a297791f09b79640e27934

interface WeatherAPI {

    @GET("data/2.5/weather")
    fun getWeather(@Query("q") cityCountry : String,
                  @Query("units") unit: String,
                  @Query("appid") appid: String
                    ) : Call<Base>

}