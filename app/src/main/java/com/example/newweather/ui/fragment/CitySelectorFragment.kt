package com.example.newweather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.newweather.databinding.FragmentCitySelectorBinding

class CitySelectorFragment(private val listener: SelectedCity) : DialogFragment() {
    private lateinit var binding: FragmentCitySelectorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitySelectorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectButton.setOnClickListener {
            val resultCity = binding.tvAutoComplate.text.toString()
            listener.listener(resultCity)
            onDestroyView()
        }
    }
    interface SelectedCity {
        fun listener(location: String)
    }
}