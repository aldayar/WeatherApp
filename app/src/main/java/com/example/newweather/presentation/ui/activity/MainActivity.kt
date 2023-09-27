package com.example.newweather.presentation.ui.activity

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
import androidx.lifecycle.lifecycleScope
import com.example.newweather.databinding.ActivityMainBinding
import com.example.newweather.domain.model.WeatherResponse
import com.example.newweather.presentation.core.UIState
import com.example.newweather.presentation.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel by lazy { ViewModelProvider(this)[WeatherViewModel::class.java] }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        showWeather()
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
        weatherViewModel.getWeather("Bishkek")
        lifecycleScope.launch {
            weatherViewModel.weatherData.collect {
                when (it) {
                    is UIState.Empty -> showEmptyState()
                    is UIState.Error -> showErrorState(it.msg)
                    is UIState.Loading -> showLoadingState()
                    is UIState.Success -> showSuccesState(it.data!!)
                }

            }
        }
    }

    private fun showErrorState(errorMessage: String?) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, "Something went wrong: $errorMessage", Toast.LENGTH_SHORT).show()
        Log.e("ololo", "Error: $errorMessage")
    }

    private fun showLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
        Toast.makeText(this, "Loading... pls wait", Toast.LENGTH_SHORT).show()
    }

    private fun showSuccesState(weatherData: WeatherResponse) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, "Current weather", Toast.LENGTH_SHORT).show()
        binding.tvDagre.text = weatherData.current.temp_c.toString()
        binding.tvCityInfo.text = weatherData.location.name
    }

    private fun showEmptyState() {
        binding.progressBar.visibility = View.VISIBLE
    }
}