package com.example.menu.serializers

import com.google.gson.annotations.SerializedName

data class VisitList (
    @SerializedName("data") val data : List<SubVisitList>
)