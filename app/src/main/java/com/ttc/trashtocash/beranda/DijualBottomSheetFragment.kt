package com.ttc.trashtocash.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ttc.trashtocash.databinding.FragmentDijualBottomSheetBinding


class DijualBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): DijualBottomSheetFragment =
            DijualBottomSheetFragment().apply {
                /*
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_COUNT, itemCount)
                }
                 */
            }

    }

    private var _binding: FragmentDijualBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDijualBottomSheetBinding.inflate(inflater, container, false)


        val name =  arguments?.getString("keyname")
        val berat = arguments?.getString("keyberat")
        val harga = arguments?.getString("keyharga")
        val image = arguments?.getString("keyimage")
        val notelp = arguments?.getString("keynotelp")
        val lokasi = arguments?.getString("keylokasi")
        val jemput = arguments?.getString("keyjemput")
        val jual = arguments?.getString("keyjual")

        binding.bsItemName.setText(name)
        binding.bsItemWeight.setText(berat)
        binding.bsItemHarga.setText(harga)
        Glide.with(requireContext())
            .load(image)
            .into(binding.bsItemImg)

        binding.bsBelibtn.setOnClickListener {
            val action = PilihSampahFragmentDirections.actionPilihSampahFragmentToKonfirmasiInfoFragment(harga,berat,name,jemput,"Waiting for input",lokasi,notelp,"","botol")
            findNavController().navigate(action)

            dismiss()
        }

        return binding.root
    }
}