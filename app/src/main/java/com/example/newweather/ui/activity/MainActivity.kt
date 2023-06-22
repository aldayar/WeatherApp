package com.example.newweather.ui.activity

import android.os.Build
import android.os.Bundle
import android.text.format.DateUtils.formatDateTime
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.example.newweather.R
import com.example.newweather.core.UIState
import com.example.newweather.data.model.WeatherResponse
import com.example.newweather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: WeatherAdapter by lazy { WeatherAdapter() }
    private val weatherViewModel: WeatherViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        intiRcView()

        weatherViewModel.weatherLiveData.observe(this) { state ->
            when (state) {
                is UIState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UIState.Success->{
                   state.data?.let { showWeather(it) }
                }
                is UIState.Error->{
                    Log.e(state.msg.toString(),"ololo" )
                    Toast.makeText(this, "Failed while loading, try again", Toast.LENGTH_SHORT).show()
                }
            }
            weatherViewModel.getCurrentWeather("Bishkek")
        }

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
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showWeather(weatherResponse: WeatherResponse) = with(binding){

        val dateTime = parseEpochTime(weatherResponse.currentWeather.epochTime)
        val formattedDateTime = formatDateTime(dateTime, "dd:MM:yyyy HH:mm")
        val dayOfWeek = dateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        tvDayOfWeek.text = dayOfWeek
        tvDate.text = formattedDateTime
        tvWeather.text = weatherResponse.currentWeather.condition.text
        tvHumidity.text = "${weatherResponse.currentWeather.humidity}"
        tvWindSpeed.text = "${weatherResponse.currentWeather.wind_kph}"
        tvPressure.text = "${weatherResponse.currentWeather.pressure_mb}"
    }

}