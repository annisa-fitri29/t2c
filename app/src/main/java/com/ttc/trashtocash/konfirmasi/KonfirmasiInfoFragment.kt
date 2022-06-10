package com.ttc.trashtocash.konfirmasi

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ttc.trashtocash.adapter.ProdukAdapter
import com.ttc.trashtocash.akun.EditAkunFragmentDirections
import com.ttc.trashtocash.alertdialog.BatalTransaksiDialogFragment
import com.ttc.trashtocash.databinding.FragmentKonfirmasiInfoBinding
import com.ttc.trashtocash.register.Order
import com.ttc.trashtocash.register.Pengepul
import com.ttc.trashtocash.register.Penyetor
import com.ttc.trashtocash.register.Produk
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class KonfirmasiInfoFragment : Fragment() {

    val args: KonfirmasiInfoFragmentArgs by navArgs()

    private var _binding: FragmentKonfirmasiInfoBinding? = null
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
        _binding = FragmentKonfirmasiInfoBinding.inflate(inflater, container, false)

        //configure progress dialog
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Uploading PickUp request...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        //firebaseUid = firebaseAuth.uid.toString()


        //firebase re
        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("order")

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

        binding.infoKonfirmbtn.setOnClickListener {
            inputData()
        }

        binding.infoBatalbtn.setOnClickListener {
            var dialog = BatalTransaksiDialogFragment()

            dialog.show(parentFragmentManager, "BatalTransaksiDialogFragment")
        }

        updateInfo()

        return binding.root
    }

    private fun updateInfo(){
        binding.infoClocktxt.setText("${args.waktujemput}")
        binding.infoCalendartxt.setText("${args.tgljemput}")
        binding.infoLocationtxt.setText("${args.alamat}")
        binding.infoPhoneNumbertxt.setText("${args.notelp}")
        binding.infoPenjualanlisttxt.setText("${args.jenis} ${args.berat} Kg")
        binding.infoHargalisttxt.setText("Rp. ${args.harga}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun inputData(){
        val waktu_jemput = args.waktujemput
        val no_telp = args.notelp
        val tgl_jemput = args.tgljemput
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
        var img_link = "https://firebasestorage.googleapis.com/v0/b/sisteminformasi-449ae.appspot.com/o/item%2Fkertasdefault.png?alt=media&token=f7ec5844-b848-4b59-9fd4-4d722cc869df"
        if(args.sampah == "besi"){
            img_link = "https://firebasestorage.googleapis.com/v0/b/sisteminformasi-449ae.appspot.com/o/item%2Fbesidefault.jpg?alt=media&token=3fd329a5-419a-4f47-ad6c-b0b4fb5f06e7"
        }else if(args.sampah == "plastik"){
            img_link = "https://firebasestorage.googleapis.com/v0/b/sisteminformasi-449ae.appspot.com/o/item%2Fplastikdefault.jpg?alt=media&token=1f4ffebd-3bf6-4d96-a947-8f29f317c7a2"
        }else if(args.sampah == "botol"){
            img_link = "https://firebasestorage.googleapis.com/v0/b/sisteminformasi-449ae.appspot.com/o/item%2Fbotolkacadefault.jpg?alt=media&token=e920b068-4653-42a8-a2b5-a817182f9130"
        }else if(args.sampah == "elektronik"){
            img_link = "https://firebasestorage.googleapis.com/v0/b/sisteminformasi-449ae.appspot.com/o/item%2Felektronikdefault.jpg?alt=media&token=9be3c187-c134-4600-ac1f-4aac919d8828"
        }

            database.child("${maxId+42001}").
            setValue(
                Order(firebaseUid,formatted,tgl_jemput,waktu_jemput,no_telp,jenis,"${berat} Kg","Rp. ${harga}",alamat,tambahan,img_link)
            ).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"Order input success", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { e->
                //register failed
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"Input failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }

        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("riwayat_transaksi")
        database.child("${firebaseUid}/dikemas/${maxId+42001}").
        setValue(
            Order(firebaseUid,formatted,tgl_jemput,waktu_jemput,no_telp,jenis,"${berat} Kg","Rp. ${harga}",alamat,tambahan,img_link)
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