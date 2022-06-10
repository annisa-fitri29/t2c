package com.ttc.trashtocash.akun

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ttc.trashtocash.databinding.FragmentAkunSayaBinding


class AkunSayaFragment : Fragment() {

    private var _binding: FragmentAkunSayaBinding? = null
    private val binding get() = _binding!!

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAkunSayaBinding.inflate(inflater, container, false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()


        val builder = AlertDialog.Builder(requireContext())

        val title = SpannableString("LOGOUT")
        title.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            title.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        builder.setTitle(title)
        builder.setMessage("Are you sure you want to logout?")

        val keluar = SpannableString("Keluar")
        keluar.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            keluar.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        builder.setPositiveButton(keluar) { dialog, which ->
            firebaseAuth.signOut()
            Toast.makeText(requireContext(),
                "logged out", Toast.LENGTH_SHORT).show()
            moveToStart()
        }

        builder.setNegativeButton("Batal") { dialog, which ->

        }
        binding.keluartxt.setOnClickListener {
            //firebaseAuth.signOut()
            //moveToStart()
           // builder.show()

        }

        return binding.root
    }

    private fun moveToStart(){
        //val action = AkunSayaFragmentDirections.actionAkunSayaFragmentToStartFragment()
        //findNavController().navigate(action)
    }

}