package com.ttc.trashtocash.start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ttc.trashtocash.PengepulActivity
import com.ttc.trashtocash.PenyetorActivity
import com.ttc.trashtocash.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.loginBtn.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.registBtn.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        return binding.root
    }

    private fun moveToBeranda(){
        val action = StartFragmentDirections.actionStartFragmentToBerandaFragment()
        findNavController().navigate(action)
    }

    private fun checkUser(){
        //if user already logged in, go to beranda
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            //user is already logged in
            var firebaseUid = firebaseAuth.uid.toString()
            if(firebaseUid == "NnrBYAFz4uaSEXAxEslAYeXVNUJ3"){
                val intent = Intent (requireActivity(), PengepulActivity::class.java)
                requireActivity().startActivity(intent)
            }else if (firebaseUid == "FEosBab2pwbxO3Mopf7Dxk8888r2"){
                val intent = Intent (requireActivity(), PenyetorActivity::class.java)
                requireActivity().startActivity(intent)
            }else{
                moveToBeranda()
            }
        }
    }


}