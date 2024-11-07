package com.example.supabasealgorythm

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.result.PostgrestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView = findViewById(R.id.recyclerview)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        employeeAdapter = EmployeeAdapter(emptyList())
//        recyclerView.adapter = employeeAdapter

                //loadEmployees()  // Загружаем сотрудников при старте
     getNameAndGender()
    }

    private fun getNameAndGender() {
        CoroutineScope(Dispatchers.Main).launch {
            val nametext = findViewById<TextView>(R.id.employeename)
            val gender = findViewById<TextView>(R.id.employeegender)
            val position = findViewById<TextView>(R.id.positionname)

            try {
                val employee = SupabaseConnection.client
                .postgrest["employees"]
                .select(
                    columns = Columns.raw(
                        """
                        id,
                        fullname,
                        age,
                        gender_id,
                        position_id,
                        gender(id, name),
                        position(id, name, salary),
                        work_hours  
                """.trimIndent()
                    )
                )
                .decodeSingle<Employee>()

            Log.d("tagMessage", "Gender Name from DB: ${employee.gender?.name}")
            Log.d("tagMessage", "Position Name from DB: ${employee.position?.name}")
            Log.d("tagMessage", "Work Hours: ${employee.work_hours}")


            nametext.text = employee.name
            gender.text = employee.gender?.name
            position.text = employee.work_hours.toString()
        } catch (e: Exception) {
                Log.d("tagMessage", "Error fetching employee data", e)
        }
        }
    }
}