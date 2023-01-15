package com.example.project.drugs.model

import java.io.Serializable

class Drug(
    val id: String,
    val name: String,
    val day: String,
    val time: String,
    val dose: String,
    val doseType: String,
    val imageUrl: String,
) : Serializable {
}