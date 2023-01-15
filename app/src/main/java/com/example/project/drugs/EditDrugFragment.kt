package com.example.project.drugs

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.drugs.viewModel.DrugViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditDrugFragment : Fragment() {
    private val viewModel: DrugViewModel by activityViewModels()
    private lateinit var imageList : ArrayList<String>

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var color_purple = Color.parseColor("#FF6200EE")


        val view = inflater.inflate(R.layout.fragment_edit_drug, container, false)
        val but= view.findViewById<Button>(R.id.cancelbutton)
        val name = view.findViewById<EditText>(R.id.edit_drug_name).text
        val time = view.findViewById<EditText>(R.id.tv_medicine_time).text
        val dose= view.findViewById<EditText>(R.id.tv_dose_quantity).text
        var day = ""
        var doseType = ""
        val everyday_cb = view.findViewById<CheckBox>(R.id.checkBox)
        val monday_cb = view.findViewById<AppCompatCheckBox>(R.id.cb_monday)
        val tuesday_cb = view.findViewById<AppCompatCheckBox>(R.id.cb_tuesday)
        val wed_cb = view.findViewById<AppCompatCheckBox>(R.id.cb_wednesday)
        val thu_cb = view.findViewById<AppCompatCheckBox>(R.id.cd_thursday)
        val fri_cb = view.findViewById<AppCompatCheckBox>(R.id.cb_friday)
        val sat_cb = view.findViewById<AppCompatCheckBox>(R.id.cb_saturday)
        val sun_cb = view.findViewById<AppCompatCheckBox>(R.id.cb_sunday)
        fun button_all_checked (){
            monday_cb.isChecked = true
            tuesday_cb.isChecked = true
            wed_cb.isChecked = true
            thu_cb.isChecked = true
            fri_cb.isChecked = true
            sat_cb.isChecked = true
            sun_cb.isChecked = true
            monday_cb.setBackgroundColor(color_purple)
            tuesday_cb.setBackgroundColor(color_purple)
            wed_cb.setBackgroundColor(color_purple)
            thu_cb.setBackgroundColor(color_purple)
            fri_cb.setBackgroundColor(color_purple)
            sat_cb.setBackgroundColor(color_purple)
            sun_cb.setBackgroundColor(color_purple)
        }
        fun button_all_checked_reverse(){
            monday_cb.isChecked = false
            tuesday_cb.isChecked = false
            wed_cb.isChecked = false
            thu_cb.isChecked = false
            fri_cb.isChecked = false
            sat_cb.isChecked = false
            sun_cb.isChecked = false
            monday_cb.setBackgroundColor(Color.WHITE)
            tuesday_cb.setBackgroundColor(Color.WHITE)
            wed_cb.setBackgroundColor(Color.WHITE)
            thu_cb.setBackgroundColor(Color.WHITE)
            fri_cb.setBackgroundColor(Color.WHITE)
            sat_cb.setBackgroundColor(Color.WHITE)
            sun_cb.setBackgroundColor(Color.WHITE)
        }
        everyday_cb.setOnClickListener {
            if (everyday_cb.isChecked){
                day = "Everyday"
                button_all_checked()
            }else {
                day = ""
                button_all_checked_reverse()
            }
        }
        monday_cb.setOnClickListener {
            if (monday_cb.isChecked){
                day = "Every Monday"
                button_all_checked_reverse()
                monday_cb.setBackgroundColor(color_purple)
            }else {
                day = ""
                monday_cb.setBackgroundColor(Color.WHITE)
                button_all_checked_reverse()
            }
        }
        tuesday_cb.setOnClickListener {
            if (tuesday_cb.isChecked){
                day = "Every Tuesday"
                button_all_checked_reverse()
                tuesday_cb.setBackgroundColor(color_purple)
            }else {
                day = ""
                tuesday_cb.setBackgroundColor(Color.WHITE)
                button_all_checked_reverse()
            }
        }

        wed_cb.setOnClickListener {
            if (wed_cb.isChecked){
                day = "Every Wednesday"
                button_all_checked_reverse()
                wed_cb.setBackgroundColor(color_purple)
            }else {
                day = ""
                wed_cb.setBackgroundColor(Color.WHITE)
                button_all_checked_reverse()
            }
        }

        thu_cb.setOnClickListener {
            if (thu_cb.isChecked){
                day = "Every Thursday"
                button_all_checked_reverse()
                thu_cb.setBackgroundColor(color_purple)
            }else {
                day = ""
                thu_cb.setBackgroundColor(Color.WHITE)
                button_all_checked_reverse()
            }
        }
        fri_cb.setOnClickListener {
            if (fri_cb.isChecked){
                day = "Every Friday"
                button_all_checked_reverse()
                fri_cb.setBackgroundColor(color_purple)
            }else {
                day = ""
                fri_cb.setBackgroundColor(Color.WHITE)
                button_all_checked_reverse()
            }
        }
        sat_cb.setOnClickListener {
            if (sat_cb.isChecked){
                day = "Every Saturday"
                button_all_checked_reverse()
                sat_cb.setBackgroundColor(color_purple)
            }else {
                day = ""
                sat_cb.setBackgroundColor(Color.WHITE)
                button_all_checked_reverse()
            }
        }
        sun_cb.setOnClickListener {
            if (sun_cb.isChecked){
                day = "Every Sunday"
                button_all_checked_reverse()
                sun_cb.setBackgroundColor(color_purple)
            }else {
                day = ""
                sun_cb.setBackgroundColor(Color.WHITE)
                button_all_checked_reverse()
            }
        }

        imageList = arrayListOf(
            "https://firebasestorage.googleapis.com/v0/b/cs394project2.appspot.com/o/Pills-3209654.jpg?alt=media&token=76c4e2b2-7f87-4817-ae5e-0541b316dbb4",
            "https://firebasestorage.googleapis.com/v0/b/cs394project2.appspot.com/o/d41586-021-02783-1_19740150.jpg?alt=media&token=f61e5c34-ac73-45ac-b61b-6384f5906185\n",
            "https://firebasestorage.googleapis.com/v0/b/cs394project2.appspot.com/o/Pills-3209654.jpg?alt=media&token=76c4e2b2-7f87-4817-ae5e-0541b316dbb4\n",
            "https://firebasestorage.googleapis.com/v0/b/cs394project2.appspot.com/o/Pills-3209654.jpg?alt=media&token=76c4e2b2-7f87-4817-ae5e-0541b316dbb4\n",
            "https://firebasestorage.googleapis.com/v0/b/cs394project2.appspot.com/o/Pills-3209654.jpg?alt=media&token=76c4e2b2-7f87-4817-ae5e-0541b316dbb4\n"
        )


        but.setOnClickListener{
            doseType = view.findViewById<Spinner>(R.id.spinner_dose_units).selectedItem.toString()

            //viewModel.drugs.value?.add(Drug(name.toString(), day, time.toString(), dose.toString() ,doseType))
            saveFireStore(name.toString(), day, time.toString(), dose.toString() ,doseType)



        }



        return view

    }

    fun saveFireStore(name: String, day: String, time:String , dose:String, doseType:String) {

        val index = (0 until 5).random()
        val db = FirebaseFirestore.getInstance()
        val data: MutableMap<String, Any> = HashMap()
        data["name"] = name
        data["day"] = day
        data["time"] = time
        data["dose"] = dose
        data["doseType"] = doseType
        data["imageUrl"] = imageList[index]

        val userID = FirebaseAuth.getInstance().currentUser?.uid;

        db.collection("users").document(userID.toString()).collection("medicines")
            .add(data)
            .addOnSuccessListener {
                //Toast.makeText()
                findNavController().navigate(R.id.action_editDrugFragment_to_homePageFragment)
            }
            .addOnFailureListener{
            }
        //readFireStoreData()
    }

}
