package com.example.assignment_rk_2_ead_2.beans

import java.io.Serializable

data class User(
    val id: Long,
    val email: String,
    val name: String,
    val password: String
) : Serializable
