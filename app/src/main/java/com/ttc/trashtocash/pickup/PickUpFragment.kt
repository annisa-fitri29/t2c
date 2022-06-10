package com.ttc.trashtocash.pickup

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ttc.trashtocash.R
import com.ttc.trashtocash.akun.TambahProdukFragmentDirections
import com.ttc.trashtocash.databinding.FragmentPickUpBinding
import java.text.SimpleDateFormat
import java.util.*

class PickUpFragment : Fragment() {

    val args: PickUpFragmentArgs by navArgs()

    private var _binding: FragmentPickUpBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val waktuambil = resources.getStringArray(R.array.waktu_ambil)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, waktuambil)
        binding.waktuAutocomplete.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPickUpBinding.inflate(inflater, container, false)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayofMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofMonth)
            updateLable(myCalendar)
        }


        binding.tanggalPickupTextInputLayout.setEndIconOnClickListener {
            DatePickerDialog(requireContext(), datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.pilihkertasBTN.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToPilihJenisFragment("pickup")
            findNavController().navigate(action)


        }
        binding.pilihBesiBTN.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukBesiFragment("pickup")
            findNavController().navigate(action)
        }

        binding.pilihBotolKacaBTN.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukBotolFragment("pickup")
            findNavController().navigate(action)
        }


        binding.pilihPlastikBTN.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukPlastikFragment("pickup")
            findNavController().navigate(action)
        }

        binding.pilihElektronikBTN.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukElektronikFragment("pickup")
            findNavController().navigate(action)
        }


        //***DIPILIH***//
        binding.pilihkertasBTN2.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToPilihJenisFragment("pickup")
            findNavController().navigate(action)

        }
        binding.pilihBesiBTN2.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukBesiFragment("pickup")
            findNavController().navigate(action)
        }

        binding.pilihBotolKacaBTN2.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukBotolFragment("pickup")
            findNavController().navigate(action)
        }


        binding.pilihPlastikBTN2.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukPlastikFragment("pickup")
            findNavController().navigate(action)
        }

        binding.pilihElektronikBTN2.setOnClickListener {
            val action = PickUpFragmentDirections.actionPickUpFragmentToTambahProdukElektronikFragment("pickup")
            findNavController().navigate(action)
        }

        binding.konfirmPickupBTN.setOnClickListener {
            var waktujemput = binding.waktuAutocomplete.text.toString()
            var tgljemput = binding.ettanggalPickup.text.toString()
            var alamat = binding.etalamatPickup.text.toString()
            var notelp = binding.etnoponselPickup.text.toString()
            var tambahan = binding.etinfotambahanPickup.text.toString()
            val action = PickUpFragmentDirections.actionPickUpFragmentToKonfirmasiInfoFragment(args.harga,args.berat,args.jenis,waktujemput,tgljemput,alamat,notelp,tambahan,args.sampah)
            findNavController().navigate(action)
        }

        updateBTN()

        return binding.root
    }

    private fun updateLable(myCalendar: Calendar){
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy")
        val dateTime = sdf.format(myCalendar.time)
        binding.ettanggalPickup.setText(dateTime.toString())
    }

    private fun updateBTN(){
        if(args.harga != ""){

            var harga = args.harga
            var berat = args.berat
            var jenis = args.jenis
            binding.konfirmPickupBTN.setText("Konfirmasi PickUp $jenis, Rp. $harga")
            if(args.sampah == "besi"){
                binding.pilihBesiBTN.visibility = View.INVISIBLE
                binding.pilihBesiBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "kertas"){
                binding.pilihkertasBTN.visibility = View.INVISIBLE
                binding.pilihkertasBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "plastik"){
                binding.pilihPlastikBTN.visibility = View.INVISIBLE
                binding.pilihPlastikBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "botol"){
                binding.pilihBotolKacaBTN.visibility = View.INVISIBLE
                binding.pilihBotolKacaBTN2.visibility = View.VISIBLE
            }else if(args.sampah == "elektronik"){
                binding.pilihElektronikBTN.visibility = View.INVISIBLE
                binding.pilihElektronikBTN2.visibility = View.VISIBLE
            }
        }

    }

}