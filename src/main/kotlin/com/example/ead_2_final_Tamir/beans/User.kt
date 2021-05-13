package com.example.ead_2_final_Tamir.beans

import java.io.Serializable

data class User(
    val id: Long,
    val email: String,
    val name: String,
    val password: String
) : Serializable
