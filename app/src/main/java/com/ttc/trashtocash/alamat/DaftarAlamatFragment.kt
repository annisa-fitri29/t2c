package com.ttc.trashtocash.alamat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ttc.trashtocash.R
import com.ttc.trashtocash.databinding.FragmentDaftarAlamatBinding
import com.ttc.trashtocash.databinding.FragmentPickUpBinding
import com.ttc.trashtocash.pickup.PickUpFragmentDirections

class DaftarAlamatFragment : Fragment() {

    private var _binding: FragmentDaftarAlamatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDaftarAlamatBinding.inflate(inflater, container, false)

        binding.pilihBTN.setOnClickListener {
            val action = DaftarAlamatFragmentDirections.actionDaftarAlamatFragmentToPickUpFragment("","","")
            findNavController().navigate(action)
        }

        return binding.root
    }

}