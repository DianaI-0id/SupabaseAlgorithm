package com.example.supabasealgorythm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supabasealgorythm.R
import com.example.supabasealgorythm.Employee

class EmployeeAdapter(private var employeeList: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.fullnametext)
        val position: TextView = itemView.findViewById(R.id.positionid)
        val workedHours: TextView = itemView.findViewById(R.id.workedhours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employeeList[position]
        holder.fullName.text = employee.name
        holder.position.text = employee.positionId.toString() // Замените на соответствующее название должности
        holder.workedHours.text = employee.hoursWorked.toString()
    }

    override fun getItemCount(): Int = employeeList.size

    fun updateEmployeeList(newEmployeeList: List<Employee>) {
        employeeList = newEmployeeList
        notifyDataSetChanged()
    }

    fun updateEmployees(newEmployees: List<Employee>) {
        employeeList = newEmployees
        notifyDataSetChanged()
    }
}
