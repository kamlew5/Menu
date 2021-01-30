package com.example.menu.network.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.R
import com.example.menu.serializers.ExamList

class ExamAdapter(val examList: ExamList) : RecyclerView.Adapter<ExamAdapter.ExamViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        return ExamViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.exam_list_element, parent, false)
        )
    }

    override fun getItemCount() = examList.data.size

    override fun onBindViewHolder(holder: ExamAdapter.ExamViewHolder, position: Int) {
        val exams = examList.data[position]
        holder.itemView.findViewById<TextView>(R.id.exam_list_id).text = exams.exam_id.toString()
        holder.itemView.findViewById<TextView>(R.id.exam_list_patient_name).text = exams.pacjent_imie_nazwisko
        holder.itemView.findViewById<TextView>(R.id.exam_list_worker_name).text = exams.pracownicy_imie_nazwisko
        holder.itemView.findViewById<TextView>(R.id.exam_list_exam_name).text = exams.spis_zabiegi_nazwa
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.view.context,exams.exam_id.toString(),Toast.LENGTH_SHORT).show()

        }
    }
    class ExamViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
