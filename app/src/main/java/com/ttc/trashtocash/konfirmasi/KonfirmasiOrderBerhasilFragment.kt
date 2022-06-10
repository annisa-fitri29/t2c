package com.ttc.trashtocash.konfirmasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ttc.trashtocash.databinding.FragmentKonfirmasiOrderBerhasilBinding


class KonfirmasiOrderBerhasilFragment : Fragment() {

    private var _binding: FragmentKonfirmasiOrderBerhasilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKonfirmasiOrderBerhasilBinding.inflate(inflater, container, false)

        binding.selesaiKonfirmbtn.setOnClickListener {
            val action = KonfirmasiOrderBerhasilFragmentDirections.actionKonfirmasiOrderBerhasilFragmentToBerandaFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }


}