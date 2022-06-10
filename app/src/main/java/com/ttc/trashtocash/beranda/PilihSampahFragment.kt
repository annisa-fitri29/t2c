package com.ttc.trashtocash.beranda

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ttc.trashtocash.databinding.FragmentPilihSampahBinding

class PilihSampahFragment : Fragment() {

    val args: PilihSampahFragmentArgs by navArgs()

    private var _binding: FragmentPilihSampahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPilihSampahBinding.inflate(inflater, container, false)

        binding.psBelibtn.setOnClickListener {
            var bottomSheet = DijualBottomSheetFragment.newInstance()

            val bundle = Bundle()
            bundle.putString("keyname", args.name)
            bundle.putString("keyberat", args.berat)
            bundle.putString("keyharga", args.harga)
            bundle.putString("keyimage", args.image)
            bundle.putString("keynotelp", args.notelp)
            bundle.putString("keylokasi",args.lokasi)
            bundle.putString("keyjemput", args.tgljemput)
            bundle.putString("keyjual", args.tgljual)

            bottomSheet.arguments = bundle

            bottomSheet.show(childFragmentManager, "ItemDijual")
        }

        binding.SelectedUser.setOnClickListener {
            val action = PilihSampahFragmentDirections.actionPilihSampahFragmentToMapsTestFragment()
            findNavController().navigate(action)
        }

        binding.psHubungibtn.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=\" + \"+6282188830680"
            var Browserintent = Intent (Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(Browserintent)
        }

        updateLayout()

        return binding.root
    }

    private fun updateLayout(){
        binding.psItemName.setText(args.name)
        binding.psItemWeight.setText(args.berat)
        binding.psItemHarga.setText("Rp. ${args.harga}")
        Glide.with(requireContext())
            .load(args.image)
            .into(binding.psKertas)
    }
}