package com.example.smarthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.smarthome.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var _gateOpened = false
    private var _doorOpened = false

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
            }
            else {
                _gateOpened = true
                binding.buttonControlGate.text = "Закрыть"
                binding.imageViewGate.setImageResource(R.drawable.gate_open)
            }
        }

        binding.buttonEntranceDoor.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            if (_doorOpened) {
                _doorOpened = false
                binding.buttonEntranceDoor.text = "Открыть"
                binding.imageViewDoor.setImageResource(R.drawable.door_closed)
            }
            else {
                _doorOpened = true
                binding.buttonEntranceDoor.text = "Закрыть"
                binding.imageViewDoor.setImageResource(R.drawable.door_open)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}