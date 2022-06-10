package com.ttc.trashtocash.pengepul

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ttc.trashtocash.R
import com.ttc.trashtocash.adapter.RiwayatDijemputPengepulAdapter
import com.ttc.trashtocash.alertdialog.BatalTransaksiDialogFragment
import com.ttc.trashtocash.databinding.FragmentDijemputPengepulBinding
import com.ttc.trashtocash.pengepul2.RiwayatDikemasPengepul


class DijemputPengepulFragment : Fragment() {

    private var _binding: FragmentDijemputPengepulBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var dbref : DatabaseReference
    //recyclerview
    private lateinit var dijemputRecyclerview : RecyclerView
    private lateinit var dijemputArrayList : ArrayList<RiwayatDikemasPengepul>
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUid = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDijemputPengepulBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.pengepulViewPager)

        binding.dijemputPengepulSelesaibtn.setOnClickListener {
            viewPager?.currentItem = 1
        }

        binding.dijemputPengepulDibatalkanbtn.setOnClickListener {
            viewPager?.currentItem = 2
        }

        dijemputRecyclerview = binding.dijemputPenyetorRecyclerview
        dijemputRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        dijemputRecyclerview.setHasFixedSize(true)

        dijemputArrayList = arrayListOf<RiwayatDikemasPengepul>()
        firebaseAuth = FirebaseAuth.getInstance()
        getUserData()

        return binding.root
    }

    private fun getUserData() {
        firebaseUid = firebaseAuth.uid.toString()

        dbref = FirebaseDatabase.getInstance("https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("riwayat_transaksi/$firebaseUid/dikemas/")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val dijemput = userSnapshot.getValue(RiwayatDikemasPengepul::class.java)
                        dijemputArrayList.add(dijemput!!)

                    }

                    dijemputRecyclerview.adapter = RiwayatDijemputPengepulAdapter(dijemputArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }


}