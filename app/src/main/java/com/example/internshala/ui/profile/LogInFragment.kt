package com.example.internshala.ui.profile

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.internshala.R
import com.example.internshala.activities.MainActivity
import com.example.internshala.databinding.FragmentLogInBinding
import com.example.internshala.databinding.FragmentSignUpBinding
import com.example.internshala.firestore.FireStoreClass
import com.example.internshala.models.User
import com.example.internshala.ui.dashboard.BaseFragment
import com.google.firebase.auth.FirebaseAuth

class LogInFragment : BaseFragment() {
        private lateinit var binding : FragmentLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity() as MainActivity).setActionBarTitle(getString(R.string.title_login))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentLogInBinding.inflate(inflater,container,false)
        val root = binding.root

        binding.btnLogIn.setOnClickListener {
            if (validateForm()){
                showProgressDialog(getString(R.string.please_wait))
                loggedInUser()
            }
        }
        return root
    }


    private fun loggedInUser(){
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener{
                signInTask->
            if(signInTask.isSuccessful){
                Toast.makeText(requireContext(), "You are successfully logged in.", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(requireView()).navigate(R.id.fragment_log_in_to_fragment_profile)
                hideProgressDialog()
            }else{
                //sign in fails
                    hideProgressDialog()
                Log.e("sign in","Sign in failed",signInTask.exception!!)
            }
        }
    }

    private fun validateForm() : Boolean{
        return if (TextUtils.isEmpty(binding.etEmail.text.toString().trim() ) || TextUtils.isEmpty(binding.etEmail.text.toString().trim() )){
            Toast.makeText(requireContext(), "Please fill details.", Toast.LENGTH_SHORT).show()
            false
        }else {
            true
        }
    }
}