package com.ttc.trashtocash.register

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ttc.trashtocash.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    //database ref
    private lateinit var database: DatabaseReference
    //progress dialog
    private lateinit var progressDialog: ProgressDialog
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var firebaseUid = ""
    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.registerbutton.setOnClickListener {
            validateData()
        }

        //configure progress dialog
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging in...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        //firebaseUid = firebaseAuth.uid.toString()

        //firebase re
        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Penyetor")

        return binding.root
    }

    private fun validateData(){
        //get data
        email = binding.Registeretemail.text.toString().trim()
        password = binding.Registeretpassword.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.Registeretemail.error = "Invalid email format"
        }
        else if(TextUtils.isEmpty(password)){
            //password empty
            binding.Registeretpassword.error = "Please enter the password"
        }
        else if(password.length<6){
            //password length less than 6
            binding.Registeretpassword.error = "Password minimal 6 karakter"
        }else{
            //data valid, continue register
            firebaseSignUp()
        }
    }

    private fun inputData(){
        val nama = binding.etnamalengkap.text.toString()
        val noPonsel = binding.etnoponsel.text.toString()
        val email = binding.Registeretemail.text.toString()
        val alamat = "default"
        val kolektor = false
        firebaseUid = firebaseAuth.uid.toString()
        database.child(firebaseUid).
        setValue(Penyetor(nama,email,noPonsel,alamat,
            kolektor)).addOnSuccessListener {
            Toast.makeText(requireContext(),"data input success", Toast.LENGTH_SHORT).show()
        }
    }


    private fun firebaseSignUp() {
        //show progress dialog
        progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //register success
                progressDialog.dismiss()
                //get current user
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(requireContext(),"Account ctreated as $email", Toast.LENGTH_SHORT).show()

                //input data
                inputData()
                //buka beranda
                moveToBeranda()
            }
            .addOnFailureListener { e->
                //register failed
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"Register failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun moveToBeranda(){
        val action = RegisterFragmentDirections.actionRegisterFragmentToBerandaFragment()
        findNavController().navigate(action)
    }

}