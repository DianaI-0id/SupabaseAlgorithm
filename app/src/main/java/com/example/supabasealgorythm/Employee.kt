package com.example.supabasealgorythm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: Int,
    @SerialName("fullname") val name: String,
    val genderId: Int,
    val positionId: Int,
    @SerialName("hours_worked") val hoursWorked: Int
)
