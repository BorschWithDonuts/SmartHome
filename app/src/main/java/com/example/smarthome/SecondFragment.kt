package com.example.smarthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.smarthome.databinding.FragmentSecondBinding
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var _gateOpened = false
    private var _doorOpened = false

    private val _urlGateOpen = "http://192.168.1.55:5000/"
    private val _urlGateClose = "http://192.168.1.55:5000/"

    private val _urlDoorOpen = "http://192.168.1.55:5000/"
    private val _urlDoorClose = "http://192.168.1.55:5000/"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonControlGate.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            if (_gateOpened) {
                _gateOpened = false
                binding.buttonControlGate.text = "Открыть"
                binding.imageViewGate.setImageResource(R.drawable.gate_closed)
                httpRequest(_urlGateOpen)
            }
            else {
                _gateOpened = true
                binding.buttonControlGate.text = "Закрыть"
                binding.imageViewGate.setImageResource(R.drawable.gate_open)
                httpRequest(_urlGateClose)
            }
        }

        binding.buttonEntranceDoor.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            if (_doorOpened) {
                _doorOpened = false
                binding.buttonEntranceDoor.text = "Открыть"
                binding.imageViewDoor.setImageResource(R.drawable.door_closed)
                httpRequest(_urlDoorOpen)
            }
            else {
                _doorOpened = true
                binding.buttonEntranceDoor.text = "Закрыть"
                binding.imageViewDoor.setImageResource(R.drawable.door_open)
                httpRequest(_urlDoorClose)
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