package com.example.project.drugs.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.drugs.model.Drug
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class DrugViewModel: ViewModel() {
    val drugs = MutableLiveData<ArrayList<Drug>>(arrayListOf())
    private val drugList = arrayListOf<Drug>()
    private lateinit var dbref: CollectionReference


    fun retriveDrugs(): MutableLiveData<ArrayList<Drug>> {

    dbref = FirebaseFirestore.getInstance().collection("users")
    val uid = FirebaseAuth.getInstance().currentUser?.uid;
    dbref.document(uid.toString()).collection("medicines").get().addOnSuccessListener { documents ->
        drugList.clear()

    for (document in documents) {
        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
        drugList.add(
            Drug(
                document.id.toString(),
                document.data["name"].toString(),
                document.data["day"].toString(),
                document.data["time"].toString(),
                document.data["dose"].toString(),
                document.data["doseType"].toString(),
                document.data["imageUrl"].toString()
            )
        )
    }
     drugs.value = drugList
    }
        return drugs
    }

}
