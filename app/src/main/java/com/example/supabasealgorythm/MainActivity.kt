package com.example.supabasealgorythm

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        employeeAdapter = EmployeeAdapter(emptyList())
        recyclerView.adapter = employeeAdapter

        loadEmployees()  // Загружаем сотрудников при старте
    }

    private fun loadEmployees() {
        CoroutineScope(Dispatchers.Main).launch {
            val employees = SupabaseConnection.fetchEmployees()
            if (employees != null) {
                employeeAdapter.updateEmployees(employees)
                Log.d("tagMessage", "employees: ${employees}")
            } else {
                Log.d("tagMessage", "Failed to load employees")
            }
        }
    }
}