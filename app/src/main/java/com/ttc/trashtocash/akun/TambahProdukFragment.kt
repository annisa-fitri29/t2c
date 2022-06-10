package com.ttc.trashtocash.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ttc.trashtocash.databinding.FragmentTambahProdukBinding
import com.ttc.trashtocash.pickup.PickUpFragmentDirections


class TambahProdukFragment : Fragment() {

    val args: TambahProdukFragmentArgs by navArgs()

    private var _binding: FragmentTambahProdukBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTambahProdukBinding.inflate(inflater, container, false)

        binding.TPPilihBesiBTN.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahBesiFragment("tambahproduk")
            findNavController().navigate(action)
        }
        binding.TPPilihkertasBTN.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToPilihJenisSampahFragment("tambahproduk")
            findNavController().navigate(action)
        }

        binding.TPPilihBotolKacaBTN.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahProdukBotolFragment("tambahproduk")
            findNavController().navigate(action)
        }

        binding.TPPilihPlastikBTN.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahProdukPlastikFragment("tambahproduk")
            findNavController().navigate(action)
        }

        binding.TPPilihElektronikBTN.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahProdukElektronikFragment("tambahproduk")
            findNavController().navigate(action)
        }



        //***DIPILIH***//
        binding.TPPilihBesiBTN2.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahBesiFragment("tambahproduk")
            findNavController().navigate(action)
        }
        binding.TPPilihkertasBTN2.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToPilihJenisSampahFragment("tambahproduk")
            findNavController().navigate(action)
        }

        binding.TPPilihBotolKacaBTN2.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahProdukBotolFragment("tambahproduk")
            findNavController().navigate(action)
        }

        binding.TPPilihPlastikBTN2.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahProdukPlastikFragment("tambahproduk")
            findNavController().navigate(action)
        }

        binding.TPPilihElektronikBTN2.setOnClickListener {
            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToTambahProdukElektronikFragment("tambahproduk")
            findNavController().navigate(action)
        }

        binding.konfirmTambahProdukBTN.setOnClickListener {

            var alamat = binding.TPEtalamatPickup.text.toString()
            var notelp = binding.TPEtnoponselPickup.text.toString()
            var tambahan = binding.TPEtinfotambahanPickup.text.toString()

            val action = TambahProdukFragmentDirections.actionTambahProdukFragmentToKonfirmasiTambahProdukFragment(args.berat,args.jenis,notelp,alamat,args.harga,tambahan,args.sampah)
            findNavController().navigate(action)
        }

        updateBTN()

        return binding.root
    }

    private fun updateBTN(){

        if(args.harga != ""){

            var harga = args.harga
            var berat = args.berat
            var jenis = args.jenis

            binding.konfirmTambahProdukBTN.setText("Konfirmasi Produk $jenis, Rp. $harga")
            if(args.sampah == "besi"){
                binding.TPPilihBesiBTN.visibility = View.INVISIBLE
                binding.TPPilihBesiBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "kertas"){
                binding.TPPilihkertasBTN.visibility = View.INVISIBLE
                binding.TPPilihkertasBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "botol"){
                binding.TPPilihBotolKacaBTN.visibility = View.INVISIBLE
                binding.TPPilihBotolKacaBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "plastik"){
                binding.TPPilihPlastikBTN.visibility = View.INVISIBLE
                binding.TPPilihPlastikBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "elektronik"){
                binding.TPPilihElektronikBTN.visibility = View.INVISIBLE
                binding.TPPilihElektronikBTN2.visibility = View.VISIBLE
            }
        }

    }

}