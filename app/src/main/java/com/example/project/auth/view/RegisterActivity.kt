package com.example.project.auth.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.project.auth.AuthViewModel
import com.example.project.auth.model.MyUser
import com.example.project.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginText.setOnClickListener{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.register.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            if(email.isNotEmpty() || pass.isNotEmpty()){
                authViewModel.register(user = MyUser(email, pass))
                statusCheck()
            }else{
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }

    }

    fun statusCheck(){
        authViewModel.status.observe(this, Observer {
            if (it == true){
                val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
            }else{
                Toast.makeText(this, "Please check your informations!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}