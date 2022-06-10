package com.ttc.trashtocash.akun

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ttc.trashtocash.MainActivity
import com.ttc.trashtocash.PengepulActivity
import com.ttc.trashtocash.ProdukPenyetorActivity
import com.ttc.trashtocash.databinding.FragmentAkunPenyetorBinding

class AkunPenyetorFragment : Fragment() {

    private var _binding: FragmentAkunPenyetorBinding? = null
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
        _binding = FragmentAkunPenyetorBinding.inflate(inflater, container, false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Penyetor")

        readData()
        binding.APCardDaftarProduk.setOnClickListener {
            val action = AkunPenyetorFragmentDirections.actionAkunPenyetorFragmentToDaftarProdukPenyetorFragment()
            findNavController().navigate(action)
        }

        binding.AccPenyKeluarCard.setOnClickListener {
            firebaseAuth.signOut()
                val action = AkunPenyetorFragmentDirections.actionAkunPenyetorFragmentToStartFragment()
                findNavController().navigate(action)

        }

        binding.ubahAkunInfoPenyBTN.setOnClickListener {

            val action = AkunPenyetorFragmentDirections.actionAkunPenyetorFragmentToEditAkunFragment()
            findNavController().navigate(action)


        }

        binding.perluDikemastxt.setOnClickListener {
            val intent = Intent (requireActivity(), ProdukPenyetorActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.Dibatalkantxt.setOnClickListener {
            val intent = Intent (requireActivity(), ProdukPenyetorActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.RiwayatTransaksiPenytxt.setOnClickListener {
            val intent = Intent (requireActivity(), ProdukPenyetorActivity::class.java)
            requireActivity().startActivity(intent)
        }

        binding.APCardDaftarKolektor.setOnClickListener {
            val url = "https://docs.google.com/forms/d/e/1FAIpQLSedX_v38QWOkfE2665liMRFTbjgd1alHuxBRFdQmkIIPW2oKQ/viewform"
            var Browserintent = Intent (Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(Browserintent)
        }

        return binding.root
    }

    private fun readData(){
        firebaseUid = firebaseAuth.uid.toString()
            binding.APCardDaftarKolektor.visibility = View.VISIBLE

        database.child(firebaseUid).get().addOnSuccessListener {
            if(it.exists()){

                val name = it.child("nama").value

                val namaText = binding.accountNamepeny

                namaText.setText(name.toString())

            }else{
                Toast.makeText(requireContext(),"Data doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

}