package com.ttc.trashtocash.alertdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ttc.trashtocash.databinding.CustomDialogbatalBinding
import com.ttc.trashtocash.konfirmasi.KonfirmasiInfoFragmentDirections

class BatalTransaksiDialogFragment : DialogFragment() {

    private var _binding: CustomDialogbatalBinding? = null
    val binding get() = _binding!!

    private val TAG = "BatalTransaksiDialogFragment"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = CustomDialogbatalBinding.inflate(inflater, container, false)

        binding.noBtnbatal.setOnClickListener {
            dismiss()
        }

        binding.yesbtnBatal.setOnClickListener {
            val action = KonfirmasiInfoFragmentDirections.actionKonfirmasiInfoFragmentToPickUpFragment("","","")
            findNavController().navigate(action)

            dismiss()
        }

        return binding.root

    }

}