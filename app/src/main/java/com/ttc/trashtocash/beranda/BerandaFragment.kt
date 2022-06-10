package com.ttc.trashtocash.beranda

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.ttc.trashtocash.adapter.OrderAdapter
import com.ttc.trashtocash.adapter.ProdukAdapter
import com.ttc.trashtocash.databinding.FragmentBerandaBinding
import com.ttc.trashtocash.register.Produk
import com.ttc.trashtocash.tambahproduk.DaftarProdukPenyetorFragmentDirections
import com.vmadalin.easypermissions.EasyPermissions


class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!
    //firebase
    private lateinit var dbref : DatabaseReference
    //recyclerview
    private lateinit var produkRecyclerview : RecyclerView
    private lateinit var produkArrayList : ArrayList<Produk>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)

        //checkpermission
        hasBackgroundLocationPermission(requireContext())
        requestBackgroundPermission(this)

        produkRecyclerview = binding.produkRecyclerview
        produkRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
        produkRecyclerview.setHasFixedSize(true)

        produkArrayList = arrayListOf<Produk>()
        getUserData()

        return binding.root
    }


    private fun hasBackgroundLocationPermission(context: Context): Boolean{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            return EasyPermissions.hasPermissions(
                context,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
        return true
    }

    private fun requestBackgroundPermission(fragment: Fragment){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            EasyPermissions.requestPermissions(
                fragment,
                "test",
                3
            )
        }
    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance("https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Produk")
        var adapter = ProdukAdapter(requireContext(),produkArrayList)
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){
                        val produk = userSnapshot.getValue(Produk::class.java)
                        produkArrayList.add(produk!!)
                    }
                    produkRecyclerview.adapter = adapter
                }
                adapter.setOnItemClickListener(object : ProdukAdapter.onItemClickListener{
                    override fun onItemClick(position: Int){
                        val name: String? = snapshot.child("${position+1}/jenis").value.toString()
                        val harga = snapshot.child("${position+1}/harga").value.toString()
                        val berat = snapshot.child("${position+1}/jumlah").value.toString()
                        val notelp = snapshot.child("${position+1}/notelp").value.toString()
                        val tgljual = snapshot.child("${position+1}/tgl_jual").value.toString()
                        val image = snapshot.child("${position+1}/img_link").value.toString()
                        val action = BerandaFragmentDirections.actionBerandaFragmentToPilihSampahFragment(
                            name,berat,harga,image,"Jl. Tarakan lr.155",tgljual,"Waiting for input", notelp
                        )
                        findNavController().navigate(action)
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }

}