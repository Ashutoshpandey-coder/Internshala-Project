package com.example.internshala.utils

import com.google.firebase.auth.FirebaseAuth


class Constants {

   companion object{
       const val WORKSHOP_MODEL : String = "workshopModel"
       const val USER_DETAIL_SHARED_PREFERENCE : String = "userDetails"

       const val NAME : String = "name"
       const val PHONE : String = "phone"
       const val EMAIL : String = "email"

       fun getCurrentUserId() : String {
           val currentUser = FirebaseAuth.getInstance().currentUser
           var currentUserId = ""
           if (currentUser != null){
               currentUserId = currentUser.uid
           }
           return currentUserId
       }

   }
}