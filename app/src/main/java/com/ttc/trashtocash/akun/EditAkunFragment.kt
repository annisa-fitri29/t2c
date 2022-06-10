package com.ttc.trashtocash.akun

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
import com.ttc.trashtocash.databinding.FragmentEditAkunBinding
import com.ttc.trashtocash.register.Pengepul
import com.ttc.trashtocash.register.Penyetor


class EditAkunFragment : Fragment() {

    private var _binding: FragmentEditAkunBinding? = null
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
    private var kolektor = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentEditAkunBinding.inflate(inflater, container, false)

        //configure progress dialog
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Editing Profile...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        //firebaseUid = firebaseAuth.uid.toString()


        //firebase re
        database = FirebaseDatabase.getInstance(
            "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Penyetor")

        readData()

        binding.EAPenysaveButton.setOnClickListener {
            validateData()
        }

        return binding.root

    }

    private fun readData(){
        firebaseUid = firebaseAuth.uid.toString()
        if(firebaseUid == "NnrBYAFz4uaSEXAxEslAYeXVNUJ3"){
            database = FirebaseDatabase.getInstance(
                "https://sisteminformasi-449ae-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Pengepul")
        }
        database.child(firebaseUid).get().addOnSuccessListener {
            if(it.exists()){

                val name = it.child("nama").value
                val email = it.child("email").value
                val alamat = it.child("alamat").value
                val noTelp = it.child("noTelp").value
                kolektor = it.child("kolektor").value as Boolean

                val namaText = binding.EAPenyNameEdit
                val noPonselText = binding.EAPenyNoponselEdit
                val emailText = binding.EAPenyEmailEdit
                val alamatText = binding.EAPenyAlamatEdit

                namaText.setText(name.toString())
                noPonselText.setText(noTelp.toString())
                emailText.setText(email.toString())
                alamatText.setText(alamat.toString())


            }else{
                Toast.makeText(requireContext(),"Data doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateData(){
        //get data
        email = binding.EAPenyEmailEdit.text.toString().trim()
        password = binding.EAPenyPassEdit.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.EAPenyEmailEdit.error = "Invalid email format"
        }
        else if(TextUtils.isEmpty(password)){
            //password empty
            binding.EAPenyPassEdit.error = "Please enter the password"
        }
        else if(password.length<6){
            //password length less than 6
            binding.EAPenyPassEdit.error = "Password minimal 6 karakter"
        }else{
            //data valid, continue input
            inputData()
        }
    }

    private fun inputData(){
        val nama = binding.EAPenyNameEdit.text.toString()
        val noPonsel = binding.EAPenyNoponselEdit.text.toString()
        val email = binding.EAPenyEmailEdit.text.toString()
        val alamat = binding.EAPenyAlamatEdit.text.toString()

        firebaseUid = firebaseAuth.uid.toString()

        progressDialog.show()

        if(firebaseUid == "NnrBYAFz4uaSEXAxEslAYeXVNUJ3"){
            database.child(firebaseUid).
            setValue(
                Pengepul(nama,email,noPonsel,alamat)
            ).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"data input success", Toast.LENGTH_SHORT).show()

                val action = EditAkunFragmentDirections.actionEditAkunFragmentToAkunPenyetorFragment()
                findNavController().navigate(action)

            }.addOnFailureListener { e->
                //register failed
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"Edit profile failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }else{
            database.child(firebaseUid).
            setValue(
                Penyetor(nama,email,noPonsel,alamat,kolektor)
            ).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"data input success", Toast.LENGTH_SHORT).show()

                val action = EditAkunFragmentDirections.actionEditAkunFragmentToAkunPenyetorFragment()
                findNavController().navigate(action)

            }.addOnFailureListener { e->
                //register failed
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"Edit profile failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }

}