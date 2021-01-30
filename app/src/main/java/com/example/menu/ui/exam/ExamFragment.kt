package com.example.menu.ui.exam

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.R
import com.example.menu.network.OkHttpRequest
import com.example.menu.network.adapters.ExamAdapter
import com.example.menu.network.adapters.WorkerAdapter
import com.example.menu.serializers.ExamList
import com.example.menu.serializers.PatientList
import com.example.menu.ui.worker.WorkerViewModel
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONException
import java.io.IOException

class ExamFragment : Fragment() {

    private lateinit var examViewModel: ExamViewModel
    private lateinit var gsonExamList: ExamList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        examViewModel =
            ViewModelProvider(this).get(ExamViewModel::class.java)
        val root = inflater.inflate(R.layout.exam_fragment, container, false)
        val url = "https://sos.kml.net.pl/api/exam?id=all"
        getDetails(url)
        return root
    }

    private fun showPatients() {
        val recyclerExam: RecyclerView = requireView().findViewById(R.id.recyclerExam)
        recyclerExam.layoutManager = LinearLayoutManager(parentFragment?.context)
        recyclerExam.adapter = ExamAdapter(gsonExamList)
    }
    private fun getDetails(url:String) {
        val client = OkHttpClient()
        val request= OkHttpRequest(client)

        request.GET(url, object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Activity Failure.")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                activity?.runOnUiThread{
                    try {
                        gsonExamList = GsonBuilder().create().fromJson(responseData, ExamList::class.java)
                        println(gsonExamList)
                        this@ExamFragment.showPatients()

                    } catch (e: JSONException) {
                        e.printStackTrace()

                    }
                }
            }

        })
    }
}