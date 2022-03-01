package com.ait.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import androidx.appcompat.app.AppCompatActivity
import com.ait.weatherapp.adapter.CityRecyclerAdapter
import com.ait.weatherapp.data.Base
import com.ait.weatherapp.data.WeatherResult
import com.ait.weatherapp.databinding.ActivityDetailsBinding
import com.ait.weatherapp.retrofit.WeatherAPI
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cityName =
            intent.getStringExtra(ScrollingActivity.KEY_DATA)//intent = who has called me?
        binding.tvCityName.text = cityName
    }

    override fun onResume() {
        super.onResume()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherAPI::class.java)

        val weatherCall =  weatherService.getWeather("${binding.tvCityName.text}","metric","428921c522a297791f09b79640e27934")

        weatherCall.enqueue(object : Callback<Base> {
            override fun onFailure(call: Call<Base>, t: Throwable) {
                binding.tvWeather.text = t.message
            }

            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                var weatherResult = response.body()

                binding.tvWeather.text = weatherResult?.weather?.get(0)?.main.toString()
                binding.tvTemperature.text = getString(R.string.currently, weatherResult?.main?.temp?.toString())
                binding.tvHumidity.text = getString(R.string.humidity, weatherResult?.main?.humidity?.toString())
                binding.tvDescription.text = getString(R.string.description, weatherResult?.weather?.get(0)?.description.toString())
                binding.tvFeelsLike.text = getString(R.string.feels_like, weatherResult?.main?.feels_like?.toString())
                binding.tvMax.text = getString(R.string.max, weatherResult?.main?.temp_max?.toString())
                binding.tvMin.text = getString(R.string.min, weatherResult?.main?.temp_min?.toString())
                Glide.with(this@DetailsActivity)
                    .load(
                        ("https://openweathermap.org/img/w/" +
                                weatherResult?.weather?.get(0)?.icon
                                + ".png"))
                    .into(binding.imageView)
            }
        })

    }





}