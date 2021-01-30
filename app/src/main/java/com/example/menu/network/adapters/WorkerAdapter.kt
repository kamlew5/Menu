package com.example.menu.network.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.R
import com.example.menu.serializers.WorkerList

class WorkerAdapter(val workerList: WorkerList) : RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        return WorkerViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.patient_list_element, parent, false)
        )
    }

    override fun getItemCount() = workerList.data.size

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        val workers = workerList.data[position]
        holder.itemView.findViewById<TextView>(R.id.patient_list_id).text = workers.pracownicy_id.toString()
        holder.itemView.findViewById<TextView>(R.id.patient_list_name).text = workers.pracownicy_imie_nazwisko
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.view.context,workers.pracownicy_id.toString(),Toast.LENGTH_SHORT).show()

        }
    }
    class WorkerViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
