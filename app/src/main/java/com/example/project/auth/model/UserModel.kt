package com.example.project.auth.model

import java.io.Serializable

class MyUser(
    val email: String,
    val password: String,
) : Serializable {
}