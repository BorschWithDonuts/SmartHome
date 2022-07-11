package com.example.smarthome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smarthome.databinding.LightingWidgetBinding
import java.net.HttpURLConnection
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStream
import java.net.URL

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LightingWidget : Fragment() {

    private var _binding: LightingWidgetBinding? = null
    private var _yardLighting = false
    private var _courtyardLighting = false

    private val _urlYardLightOn = "http://192.168.1.55:5000/"
    private val _urlYardLightOff = "http://192.168.1.55:5000/"

    private val _urlCourtyardLightOn = "http://192.168.1.55:5000/"
    private val _urlCourtyardLightOff = "http://192.168.1.55:5000/"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LightingWidgetBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonYardLighting.setOnClickListener {
//            findNavController().navigate(R.id.action_LightingWidget_to_FirstFragment)
            if (_yardLighting) {
                _yardLighting = false
                binding.buttonYardLighting.text = "Включить"
                binding.imageViewYardLighting.setImageResource(R.drawable.turn_off_lights)

                httpRequest(_urlYardLightOn)
            }
            else {
                _yardLighting = true
                binding.buttonYardLighting.text = "Выключить"
                binding.imageViewYardLighting.setImageResource(R.drawable.turn_on_lights)

                httpRequest(_urlCourtyardLightOn)
            }
        }

        binding.buttonCourtyardLighting.setOnClickListener {
//            findNavController().navigate(R.id.action_LightingWidget_to_FirstFragment)
            if (_courtyardLighting) {
                _courtyardLighting = false
                binding.buttonCourtyardLighting.text = "Включить"
                binding.imageViewCourtyardLighting.setImageResource(R.drawable.turn_off_lights)

                httpRequest(_urlCourtyardLightOn)
            }
            else {
                _courtyardLighting = true
                binding.buttonCourtyardLighting.text = "Выключить"
                binding.imageViewCourtyardLighting.setImageResource(R.drawable.turn_on_lights)

                httpRequest(_urlCourtyardLightOff)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun httpRequest(url : String){
        lifecycleScope.launch {
            try {
                val client = HttpClient(CIO)
                client.get<String>(url)
            } catch (e: Exception) {
                println("Successfully failed with a ${e.javaClass}")
            }
        }
    }
}
