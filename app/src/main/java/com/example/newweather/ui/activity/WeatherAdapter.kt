package com.example.newweather.ui.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newweather.databinding.WetherInfoItemBinding

class WeatherAdapter :
    ListAdapter<WeatherModel, WeatherAdapter.WeatherViewHolder>(WeatherDiffUtil()) {
    inner class WeatherViewHolder(private val binding: WetherInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherModel: WeatherModel) {
            binding.imgItemSun.setImageResource(weatherModel.imageView)
            binding.itemTvDate.text = weatherModel.text
            binding.itemTvDegree.text = weatherModel.tempUp
            binding.itemTvDegreeDown.text = weatherModel.tempDown
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherViewHolder(
        WetherInfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherModel>() {
    override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
        return oldItem == newItem
    }
}