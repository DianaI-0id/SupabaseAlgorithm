package com.example.supabasealgorythm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: Int,
    @SerialName("fullname") val name: String,
    val age: Int,
    val gender_id: Int,
    val position_id: Int,
    val gender: Gender? = null,
    val position: Position? = null,
    val work_hours: Int
)

