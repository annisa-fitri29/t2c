package com.ttc.trashtocash.pengepul2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.ttc.trashtocash.R
import com.ttc.trashtocash.databinding.CustomDialogbatalBinding


class BatalPickUpPengFragment : DialogFragment() {

    private var _binding: CustomDialogbatalBinding? = null
    private val binding get() = _binding!!


    private val TAG = "BatalTransaksiDialogFragment"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = CustomDialogbatalBinding.inflate(inflater, container, false)

        binding.yesbtnBatal.setOnClickListener {
            val input: String = "ignore"
            val bundle = Bundle()

            bundle.putString("BatalPickUp", input)
            setFragmentResult("BatalPickUpPeng", bundle)
            dismiss()
        }

        binding.noBtnbatal.setOnClickListener {
            dismiss()
        }

        return binding.root

    }
}