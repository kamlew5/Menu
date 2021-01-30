package com.example.menu.serializers

import com.google.gson.annotations.SerializedName

data class WorkerList (
    @SerializedName("data") val data : List<SubWorkerList>
)