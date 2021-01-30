package com.example.menu.network.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.R
import com.example.menu.serializers.PatientList
import com.example.menu.serializers.VisitList

class VisitAdapter(val visitList: VisitList) : RecyclerView.Adapter<VisitAdapter.VistViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistViewHolder {
        return VistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.visit_list_element, parent, false)
        )
    }

    override fun getItemCount() = visitList.data.size

    class VistViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    override fun onBindViewHolder(holder: VistViewHolder, position: Int) {
        val visits = visitList.data[position]
        holder.itemView.findViewById<TextView>(R.id.visit_list_id).text = visits.visit_id.toString()
        holder.itemView.findViewById<TextView>(R.id.visit_list_patient_name).text = visits.pacjent_imie_nazwisko
        holder.itemView.findViewById<TextView>(R.id.visit_list_worker_name).text = visits.pracownicy_imie_nazwisko
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.view.context,visits.visit_id.toString(),Toast.LENGTH_SHORT).show()

        }
    }
}
