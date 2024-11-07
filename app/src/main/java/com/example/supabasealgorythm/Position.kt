package com.example.supabasealgorythm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val id: Int,
    val name: String?,
    val salary: Double
)
