package com.ttc.trashtocash.konfirmasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ttc.trashtocash.databinding.FragmentKonfirmasiPickUpBinding


class KonfirmasiPickUpFragment : Fragment() {

    private var _binding: FragmentKonfirmasiPickUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKonfirmasiPickUpBinding.inflate(inflater, container, false)

        binding.kpSelesaiKonfirmbtn.setOnClickListener {
            val action = KonfirmasiPickUpFragmentDirections.actionKonfirmasiPickUpFragmentToPickUpFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }


}