package com.ttc.trashtocash.pengepul

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ttc.trashtocash.MainActivity
import com.ttc.trashtocash.ProdukPenyetorActivity
import com.ttc.trashtocash.R
import com.ttc.trashtocash.akun.AkunPenyetorFragmentDirections
import com.ttc.trashtocash.databinding.ActivityMainBinding
import com.ttc.trashtocash.databinding.FragmentAkunPengepulBinding
import com.ttc.trashtocash.databinding.FragmentAkunPenyetorBinding


class AkunPengepulFragment : Fragment() {


    private var _binding: FragmentAkunPengepulBinding? = null
    private val binding get() = _binding!!

    private var firebaseUid = ""

    //database ref
    private lateinit var database: DatabaseReference

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAkunPengepulBinding.inflate(inflater, container, false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Pengepul")

        readData()

        binding.KeluarPengepul.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent (requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
        }

        binding.ubahAkunInfoPengBTN.setOnClickListener {
            //val action = AkunPengepulFragmentDirections.actionAkunPengepulFragment2ToEditAkunFragment2()
            //findNavController().navigate(action)
        }

        return binding.root
    }

    private fun readData(){
        firebaseUid = firebaseAuth.uid.toString()
        database.child(firebaseUid).get().addOnSuccessListener {
            if(it.exists()){

                val name = it.child("nama").value

                val namaText = binding.accountNamepeng

                namaText.setText(name.toString())

            }else{
                Toast.makeText(requireContext(),"Data doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }
    }


}