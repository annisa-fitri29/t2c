package com.ttc.trashtocash.tambahproduk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ttc.trashtocash.R
import com.ttc.trashtocash.databinding.FragmentKonfirmasiTambahProdukBinding
import com.ttc.trashtocash.databinding.FragmentTambahProdukBerhasilBinding


class TambahProdukBerhasilFragment : Fragment() {

    private var _binding: FragmentTambahProdukBerhasilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentTambahProdukBerhasilBinding.inflate(inflater, container, false)

        binding.selesaiTambahProdukbtn.setOnClickListener {
            val action = TambahProdukBerhasilFragmentDirections.actionTambahProdukBerhasilFragmentToDaftarProdukPenyetorFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

}