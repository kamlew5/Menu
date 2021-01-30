package com.example.menu.ui.worker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.R
import com.example.menu.network.OkHttpRequest
import com.example.menu.network.adapters.WorkerAdapter
import com.example.menu.serializers.PatientList
import com.example.menu.serializers.WorkerList
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONException
import java.io.IOException

class WorkerFragment : Fragment() {

    private lateinit var workerViewModel: WorkerViewModel
    private lateinit var gsonWorkerList: WorkerList

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        workerViewModel =
                ViewModelProvider(this).get(WorkerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_worker, container, false)
        val url = "https://sos.kml.net.pl/api/worker?id=all"
        getDetails(url)
        return root
    }

    private fun showPatients() {
        val recyclerWorker: RecyclerView = requireView().findViewById(R.id.recyclerWorker)
        recyclerWorker.layoutManager = LinearLayoutManager(parentFragment?.context)
        recyclerWorker.adapter = WorkerAdapter(gsonWorkerList)
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
                        gsonWorkerList = GsonBuilder().create().fromJson(responseData, WorkerList::class.java)
                        println(gsonWorkerList)
                        this@WorkerFragment.showPatients()

                    } catch (e: JSONException) {
                        e.printStackTrace()

                    }
                }
            }

        })
    }
}