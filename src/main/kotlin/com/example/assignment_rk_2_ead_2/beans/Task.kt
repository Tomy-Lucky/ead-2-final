package com.example.assignment_rk_2_ead_2.beans

import java.io.Serializable

data class Task(
    val id: Long,
    val title: String,
    val content: String,
    val userId: Long
) : Serializable
