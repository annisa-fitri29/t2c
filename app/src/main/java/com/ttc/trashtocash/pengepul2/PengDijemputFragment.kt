package com.ttc.trashtocash.pengepul2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ttc.trashtocash.MainActivity
import com.ttc.trashtocash.R
import com.ttc.trashtocash.adapter.ProdukAdapter
import com.ttc.trashtocash.adapter.RiwayatDijemputPengepulAdapter
import com.ttc.trashtocash.databinding.FragmentPengDijemputBinding
import com.ttc.trashtocash.pengepul.BatalPickUpFragment
import com.ttc.trashtocash.register.Produk


class PengDijemputFragment : Fragment() {

    private var _binding: FragmentPengDijemputBinding? = null
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
        _binding = FragmentPengDijemputBinding.inflate(inflater, container, false)

        var clicks = 0
        val viewPager = activity?.findViewById<ViewPager2>(R.id.pengepulViewPager2)
        var dialog = BatalPickUpFragment()

        binding.PdijemputPengepulSelesaibtn.setOnClickListener {
            viewPager?.currentItem = 2
        }

        binding.PdijemputPengepulDibatalkanbtn.setOnClickListener {
            viewPager?.currentItem = 3
        }

        dijemputRecyclerview = binding.pengDijemputRecyclerview
        dijemputRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        dijemputRecyclerview.setHasFixedSize(true)

        dijemputArrayList = arrayListOf<RiwayatDikemasPengepul>()
        firebaseAuth = FirebaseAuth.getInstance()
        getUserData()

        /*
        binding.djPKonfirmasibtn.setOnClickListener {
            binding.dkPExpandcard.visibility = View.GONE
        }

        binding.PdjHubungibtn.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=" + "+6282188830680"
            var Browserintent = Intent (Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(Browserintent)

        }

        binding.djPBatalbtn.setOnClickListener {
            dialog.show(parentFragmentManager, "BatalPickUpFragment")
            dialog.setFragmentResultListener("BatalPickUpPeng"){ key, bundle ->
                if (key == "BatalPickUpPeng"){
                    binding.dkPExpandcard.visibility = View.GONE
                }
            }
        }*/
        return binding.root
    }

    private fun getUserData() {
        firebaseUid = firebaseAuth.uid.toString()

        dbref = FirebaseDatabase.getInstance("https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("order")

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