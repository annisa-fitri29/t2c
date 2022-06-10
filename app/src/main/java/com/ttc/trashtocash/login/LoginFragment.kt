package com.ttc.trashtocash.login

import android.app.ProgressDialog
import android.content.Intent
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
import com.ttc.trashtocash.PengepulActivity
import com.ttc.trashtocash.PenyetorActivity
import com.ttc.trashtocash.ProdukPenyetorActivity
import com.ttc.trashtocash.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    //progress dialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginbutton.setOnClickListener {
            validateData()
        }

        binding.daftarktxt.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        //configure progress dialog
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging in...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    private fun validateData(){
        //get data
        email = binding.Loginetemail.text.toString().trim()
        password = binding.Loginetpassword.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.Loginetemail.error = "Invalid email format"
        }
        else if(TextUtils.isEmpty(password)){
            //no password entered
            binding.Loginetpassword.error = "Please enter the password"
        }
        else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(requireContext(),"Logged in as $email", Toast.LENGTH_SHORT).show()

                var firebaseUid = firebaseAuth.uid.toString()
                if(firebaseUid == "NnrBYAFz4uaSEXAxEslAYeXVNUJ3"||firebaseUid == "KqbX2oXRMQcv7sYtGKht4S1VOLC3"){
                    val intent = Intent (requireActivity(), PengepulActivity::class.java)
                    requireActivity().startActivity(intent)
                }else if(firebaseUid == "FEosBab2pwbxO3Mopf7Dxk8888r2"){
                    val intent = Intent (requireActivity(), PenyetorActivity::class.java)
                    requireActivity().startActivity(intent)
                }else{
                    moveToBeranda()
                }

            }
            .addOnFailureListener {e ->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(requireContext(),"Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun moveToBeranda(){
        val action = LoginFragmentDirections.actionLoginFragmentToBerandaFragment()
        findNavController().navigate(action)
    }
}