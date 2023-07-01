package com.example.newweather.ui.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.newweather.R
import com.example.newweather.core.UIState
import com.example.newweather.databinding.ActivityMainBinding
import com.example.newweather.ui.fragment.CitySelectorFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CitySelectorFragment.SelectedCity {
    private lateinit var binding: ActivityMainBinding
    private val adapter: WeatherAdapter by lazy { WeatherAdapter() }
    private val weatherViewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        intiRcView()
        showSelectingLocation()

        //дефолтный показ погоды
        weatherViewModel.getCurrentWeather("Bishkek")
        showWeather()

    }

    private fun intiRcView() {
        binding.recyclerView.adapter = adapter
        val model = listOf(
            WeatherModel(R.drawable.ic_sun, "Mon, 12", "35°C", "26°C"),
            WeatherModel(R.drawable.ic_clouds, "Tue, 22", "35°C", "27°C"),
            WeatherModel(R.drawable.ic_sun, "Wed, 22", "34°C", "29°C")
        )
        adapter.submitList(model)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseEpochTime(epochTime: Long): LocalDateTime {
        val instant = Instant.ofEpochMilli(epochTime)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDateTime(dateTime: LocalDateTime, pattern: String): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return dateTime.format(formatter)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showWeather() {
        weatherViewModel.weatherLiveData.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UIState.Success -> {
                    it.data?.let { weatherResponse ->
                        val dateTime =
                            parseEpochTime(weatherResponse.forecast.forecastday[0].date_epoch.toLong())
                        val formattedDateTime = formatDateTime(dateTime, "dd:MM:yyyy HH:mm")
                        val dayOfWeek =
                            dateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
                        binding.tvDayOfWeek.text = dayOfWeek
                        binding.tvDate.text = formattedDateTime
                        binding.tvDagre.text = weatherResponse.current.temp_c.toString()
                        binding.tvHumidity.text = "${weatherResponse.current.humidity}"
                        binding.tvWindSpeed.text = "${weatherResponse.current.wind_kph}"
                        binding.tvPressure.text = "${weatherResponse.current.pressure_mb}"
                        binding.tvSunset.text = weatherResponse.forecast.forecastday[0].astro.sunset
                        binding.tvSunrise.text =weatherResponse.forecast.forecastday[0].astro.sunrise
                        binding.tvDagreMax.text ="${weatherResponse.forecast.forecastday[0].day.maxtemp_c}"
                        binding.tvDagreMin.text = "${weatherResponse.forecast.forecastday[0].day.mintemp_c}"
                        binding.tvCityInfo.text = weatherResponse.location.name +" "+ weatherResponse.location.country
                    }
                }
                is UIState.Error -> {
                    Log.e(it.msg.toString(), "ololo")
                    Toast.makeText(this, "Failed while loading, try again", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showSelectingLocation() {
        binding.tvCityInfo.setOnClickListener {
            val searchFragment = CitySelectorFragment(this)
            searchFragment.show(supportFragmentManager, "TAG")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun listener(location: String) {
        weatherViewModel.getCurrentWeather(location = location)
        weatherViewModel.weatherLiveData.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Loading location, please wait",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UIState.Success -> {
                    showWeather()
                    Toast.makeText(this@MainActivity, "You selected: $location", Toast.LENGTH_SHORT).show()
                }
                is UIState.Error -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Sorry your country doesn't exist, or try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}