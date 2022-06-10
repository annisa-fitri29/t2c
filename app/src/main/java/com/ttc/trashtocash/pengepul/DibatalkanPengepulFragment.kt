package com.ttc.trashtocash.pengepul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ttc.trashtocash.R
import com.ttc.trashtocash.adapter.RiwayatDibatalkanAdapter
import com.ttc.trashtocash.databinding.FragmentDibatalkanPengepulBinding
import com.ttc.trashtocash.pengepul2.RiwayatDibatalkanPengepul

class DibatalkanPengepulFragment : Fragment() {

    private var _binding: FragmentDibatalkanPengepulBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var dbref : DatabaseReference
    //recyclerview
    private lateinit var dijemputRecyclerview : RecyclerView
    private lateinit var dijemputArrayList : ArrayList<RiwayatDibatalkanPengepul>
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUid = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDibatalkanPengepulBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.pengepulViewPager)

        binding.dibatalkanPengepulDikemasbtn.setOnClickListener {
            viewPager?.currentItem = 0
        }

        binding.dibatalkanPengepulSelesaibtn.setOnClickListener {
            viewPager?.currentItem = 1
        }

        dijemputRecyclerview = binding.dibatalkanPenyetorRecyclerview
        dijemputRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        dijemputRecyclerview.setHasFixedSize(true)

        dijemputArrayList = arrayListOf<RiwayatDibatalkanPengepul>()
        firebaseAuth = FirebaseAuth.getInstance()
        getUserData()

        return binding.root
    }

    private fun getUserData() {
        firebaseUid = firebaseAuth.uid.toString()

        dbref = FirebaseDatabase.getInstance("https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("riwayat_transaksi/$firebaseUid/dibatalkan/")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val dijemput = userSnapshot.getValue(RiwayatDibatalkanPengepul::class.java)
                        dijemputArrayList.add(dijemput!!)

                    }

                    dijemputRecyclerview.adapter = RiwayatDibatalkanAdapter(dijemputArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}