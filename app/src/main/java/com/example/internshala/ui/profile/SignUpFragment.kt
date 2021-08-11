package com.example.internshala.ui.profile

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.internshala.R
import com.example.internshala.activities.MainActivity
import com.example.internshala.databinding.FragmentSignUpBinding
import com.example.internshala.firestore.FireStoreClass
import com.example.internshala.models.User
import com.example.internshala.ui.dashboard.BaseFragment
import com.example.internshala.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class SignUpFragment : BaseFragment() {
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity() as MainActivity).setActionBarTitle(getString(R.string.title_signup))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root = binding.root


        binding.btnSignUp.setOnClickListener {
            if (validateForm()) {
                showProgressDialog(getString(R.string.please_wait))
                registerUser()
            }
        }

        return root

    }

    private fun registerUser() {
        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Storing data into SharedPreferences
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences(
                Constants.USER_DETAIL_SHARED_PREFERENCE,
                MODE_PRIVATE
            )

        val myEdit = sharedPreferences.edit()

        myEdit.putString(Constants.NAME, name)
        myEdit.putString(Constants.PHONE, phone)
        myEdit.putString(Constants.EMAIL, email)

        myEdit.apply()



        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email

                    val user = User(
                        Constants.getCurrentUserId(),
                        name, registeredEmail!!, phone.toLong()
                    )

                    showProgressDialog(getString(R.string.please_wait))
                    FireStoreClass().registerUser(this,user)

                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(registeredEmail, password)
                        .addOnCompleteListener { signInTask ->
                            if (signInTask.isSuccessful) {
                                Toast.makeText(
                                    requireContext(),
                                    "You are successfully logged in.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Navigation.findNavController(requireView())
                                    .navigate(R.id.fragment_sign_up_to_fragment_profile)
                                hideProgressDialog()
                            } else {
                                //sign in fails
                                hideProgressDialog()
                                Log.e("sign in", "Sign in failed", task.exception!!)
                            }
                        }

                }
            }
    }

    private fun validateForm(): Boolean {
        return if (TextUtils.isEmpty(binding.etName.text.toString().trim()) || TextUtils.isEmpty(
                binding.etEmail.text.toString().trim()
            )
            || TextUtils.isEmpty(binding.etPhone.text.toString().trim()) || TextUtils.isEmpty(
                binding.etPassword.text.toString().trim()
            )
        ) {
            Toast.makeText(
                requireContext(),
                "All details are mandatory.Please fill it.",
                Toast.LENGTH_SHORT
            ).show()
            false
        } else {
            true
        }
    }

    fun userRegistrationSuccess() {
        hideProgressDialog()
//        Toast.makeText(requireContext(), "You are registered successfully", Toast.LENGTH_SHORT)
//            .show()
    }

}