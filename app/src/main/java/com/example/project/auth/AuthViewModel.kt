package com.example.project.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.auth.model.MyUser
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {
    val status = MutableLiveData<Boolean?>()

    fun login(user: MyUser) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                status.value = true
            }.addOnFailureListener{
                status.value = false
            }
    }

    fun register(user: MyUser) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener {
                status.value = true
            }.addOnFailureListener{
                status.value = false
            }
    }


}