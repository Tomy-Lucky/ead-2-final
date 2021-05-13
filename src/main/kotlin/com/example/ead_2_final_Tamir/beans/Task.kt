package com.example.ead_2_final_Tamir.beans

import java.io.Serializable

data class Task(
    val id: Long,
    val title: String,
    val content: String,
    val userId: Long
) : Serializable
