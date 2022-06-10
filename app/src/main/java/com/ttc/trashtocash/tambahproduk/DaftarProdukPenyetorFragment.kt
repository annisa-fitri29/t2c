package com.ttc.trashtocash.tambahproduk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ttc.trashtocash.adapter.OrderAdapter
import com.ttc.trashtocash.adapter.ProdukAdapter
import com.ttc.trashtocash.databinding.FragmentDaftarProdukPenyetorBinding
import com.ttc.trashtocash.register.Order


class DaftarProdukPenyetorFragment : Fragment() {

    private var _binding: FragmentDaftarProdukPenyetorBinding? = null
    private val binding get() = _binding!!
    //firebase
    private lateinit var dbref : DatabaseReference
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUid = ""
    //recyclerview
    private lateinit var orderRecyclerview : RecyclerView
    private lateinit var orderArrayList : ArrayList<Order>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDaftarProdukPenyetorBinding.inflate(inflater, container, false)

        orderRecyclerview = binding.orderRecyclerview
        orderRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
        orderRecyclerview.setHasFixedSize(true)

        orderArrayList = arrayListOf<Order>()
        getUserData()

        binding.DPPCard.setOnClickListener {
            val action = DaftarProdukPenyetorFragmentDirections.actionDaftarProdukPenyetorFragmentToTambahProdukFragment("","","")
            findNavController().navigate(action)
        }

        binding.DPPBackbutton.setOnClickListener {
            val action = DaftarProdukPenyetorFragmentDirections.actionDaftarProdukPenyetorFragmentToAkunPenyetorFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    private fun getUserData() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUid = firebaseAuth.uid.toString()
        dbref = FirebaseDatabase.getInstance("https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("riwayat_transaksi/$firebaseUid/dikemas/")
        var adapter = OrderAdapter(requireContext(),orderArrayList)

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val order = userSnapshot.getValue(Order::class.java)
                        orderArrayList.add(order!!)

                    }

                    orderRecyclerview.adapter = adapter

                }

                adapter.setOnItemClickListener(object : OrderAdapter.onItemClickListener{
                    override fun onItemClick(position: Int){

                    }
                })

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }

}