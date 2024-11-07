package com.example.supabasealgorythm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gender(
    val id: Int,
    val name: String
)
