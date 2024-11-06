package com.example.supabasealgorythm

import android.util.Log
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SupabaseConnection {
    private const val URL = "https://hctggwsxczwixriqevdu.supabase.co"
    private const val KEY =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhjdGdnd3N4Y3p3aXhyaXFldmR1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzA3ODgyMTIsImV4cCI6MjA0NjM2NDIxMn0.RXkhrJ1fvhqnLP0ti-80taeJbsFpdSlQrEKPz8Nrs5Q"

    val client = createSupabaseClient(
        supabaseUrl = URL,
        supabaseKey = KEY
    ) {
        install(Postgrest)
        install(Realtime)
    }

    suspend fun fetchEmployees(): List<Employee>? = withContext(Dispatchers.IO) {
        return@withContext try {

            val response = client.postgrest["employees"].select()
            val employees = response.decodeList<Employee>()
            Log.d("tagMessage", "Fetched employees: $employees") // Логируем полученных сотрудников
            employees
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
