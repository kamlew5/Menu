package com.example.menu.ui.visit

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
import com.example.menu.network.adapters.VisitAdapter
import com.example.menu.serializers.PatientList
import com.example.menu.serializers.VisitList
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONException
import java.io.IOException

class VisitFragment : Fragment() {

    private lateinit var visitViewModel: VisitViewModel
    private lateinit var gsonVisitList: VisitList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        visitViewModel =
            ViewModelProvider(this).get(VisitViewModel::class.java)
        val root = inflater.inflate(R.layout.visit_fragment, container, false)
        val url = "https://sos.kml.net.pl/api/visit?id=all"
        getDetails(url)
        return root
    }

    private fun showPatients() {
        val recyclerVisit: RecyclerView = requireView().findViewById(R.id.recyclerVisit)
        recyclerVisit.layoutManager = LinearLayoutManager(parentFragment?.context)
        recyclerVisit.adapter = VisitAdapter(gsonVisitList)
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
                        gsonVisitList = GsonBuilder().create().fromJson(responseData, VisitList::class.java)
                        println(gsonVisitList)
                        this@VisitFragment.showPatients()

                    } catch (e: JSONException) {
                        e.printStackTrace()

                    }
                }
            }

        })
    }
}