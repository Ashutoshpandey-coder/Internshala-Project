package com.example.internshala.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.internshala.models.User
import com.example.internshala.ui.profile.LogInFragment
import com.example.internshala.ui.profile.ProfileFragment
import com.example.internshala.ui.profile.SignUpFragment
import com.example.internshala.utils.Constants
import com.example.internshala.utils.Constants.Companion.getCurrentUserId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.security.auth.login.LoginException

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(fragment: SignUpFragment, user: User) {
        mFireStore.collection(Constants.USERS)
            .document(user.id)
            .set(user, SetOptions.merge())
            .addOnSuccessListener {
                fragment.userRegistrationSuccess()
            }.addOnFailureListener { exception ->
                fragment.hideProgressDialog()
                Log.e(fragment.javaClass.simpleName, exception.message.toString())
            }

    }
    fun getUserDetails(fragment: ProfileFragment) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                if (user != null) {
                    fragment.userLoggedInSuccess(user)
                }

            }.addOnFailureListener { exception ->
                fragment.hideProgressDialog()

                Log.e(fragment.javaClass.simpleName, exception.message.toString())
            }
    }
}