package com.example.smarthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.smarthome.databinding.LightingWidgetBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LightingWidget : Fragment() {

    private var _binding: LightingWidgetBinding? = null
    private var _yardLighting = false
    private var _courtyardLighting = false

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
            }
            else {
                _yardLighting = true
                binding.buttonYardLighting.text = "Выключить"
                binding.imageViewYardLighting.setImageResource(R.drawable.turn_on_lights)
            }
        }

        binding.buttonCourtyardLighting.setOnClickListener {
//            findNavController().navigate(R.id.action_LightingWidget_to_FirstFragment)
            if (_courtyardLighting) {
                _courtyardLighting = false
                binding.buttonCourtyardLighting.text = "Включить"
                binding.imageViewCourtyardLighting.setImageResource(R.drawable.turn_off_lights)
            }
            else {
                _courtyardLighting = true
                binding.buttonCourtyardLighting.text = "Выключить"
                binding.imageViewCourtyardLighting.setImageResource(R.drawable.turn_on_lights)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}