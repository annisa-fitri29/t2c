package com.ttc.trashtocash.tambahproduk

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ttc.trashtocash.R
import com.ttc.trashtocash.alertdialog.BatalTransaksiDialogFragment
import com.ttc.trashtocash.databinding.FragmentKonfirmasiInfoBinding
import com.ttc.trashtocash.databinding.FragmentKonfirmasiTambahProdukBinding
import com.ttc.trashtocash.konfirmasi.KonfirmasiInfoFragmentArgs
import com.ttc.trashtocash.konfirmasi.KonfirmasiInfoFragmentDirections
import com.ttc.trashtocash.register.Order
import com.ttc.trashtocash.register.Produk
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class KonfirmasiTambahProdukFragment : Fragment() {

    val args: KonfirmasiTambahProdukFragmentArgs by navArgs()

    private var _binding: FragmentKonfirmasiTambahProdukBinding? = null
    private val binding get() = _binding!!

    //database ref
    private lateinit var database: DatabaseReference
    //progress dialog
    private lateinit var progressDialog: ProgressDialog
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUid = ""

    //increment
    var maxId: Long = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKonfirmasiTambahProdukBinding.inflate(inflater, container, false)

        //configure progress dialog
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Uploading Product request...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        //firebaseUid = firebaseAuth.uid.toString()


        //firebase re
        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Produk")

        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    maxId=(snapshot.childrenCount)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        binding.tambahProdukKonfirmbtn.setOnClickListener {
            inputData()
        }

        binding.tambahProdukBatalbtn.setOnClickListener {
            var dialog = BatalTransaksiDialogFragment()

            dialog.show(parentFragmentManager, "BatalTransaksiDialogFragment")
        }


        updateInfo()

        return binding.root
    }


    private fun updateInfo(){
        binding.tambahProdukLocationtxt.setText("${args.alamat}")
        binding.tambahProdukPhoneNumbertxt.setText("${args.notelp}")
        binding.tambahProdukPenjualanlisttxt.setText("${args.jenis} ${args.berat} Kg")
        binding.tambahProdukHargalisttxt.setText("Rp. ${args.harga}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun inputData(){
        val no_telp = args.notelp
        val alamat = args.alamat
        val jenis = args.jenis
        val berat = args.berat
        val harga = args.harga
        val tambahan = args.tambahan

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val formatted = current.format(formatter)

        firebaseUid = firebaseAuth.uid.toString()

        progressDialog.show()

        database.child("${maxId+69001}").
        setValue(
            Produk(firebaseUid,formatted,no_telp,jenis,"${berat} Kg","Rp. ${harga}",alamat,tambahan)
        ).addOnSuccessListener {
            progressDialog.dismiss()
            Toast.makeText(requireContext(),"Produk input success", Toast.LENGTH_SHORT).show()

            val action = KonfirmasiTambahProdukFragmentDirections.actionKonfirmasiTambahProdukFragmentToTambahProdukBerhasilFragment()
            findNavController().navigate(action)

        }.addOnFailureListener { e->
            //register failed
            progressDialog.dismiss()
            Toast.makeText(requireContext(),"Input failed due to ${e.message}", Toast.LENGTH_SHORT).show()
        }

        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("riwayat_transaksi")
        database.child("${firebaseUid}/dikemas/${maxId+69001}").
        setValue(
            Order(firebaseUid,formatted,no_telp,jenis,"${berat} Kg","Rp. ${harga}",alamat,tambahan)
        ).addOnSuccessListener {
            progressDialog.dismiss()
            Toast.makeText(requireContext(),"Order input success", Toast.LENGTH_SHORT).show()

            val action = KonfirmasiInfoFragmentDirections.actionKonfirmasiInfoFragmentToKonfirmasiPickUpFragment()
            findNavController().navigate(action)

        }.addOnFailureListener { e->
            //register failed
            progressDialog.dismiss()
            Toast.makeText(requireContext(),"Input failed due to ${e.message}", Toast.LENGTH_SHORT).show()
        }


    }

}