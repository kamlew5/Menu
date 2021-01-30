package com.example.menu.serializers

import com.google.gson.annotations.SerializedName

data class SubWorkerList (
    @SerializedName("pracownicy_id") val pracownicy_id : Int,
    @SerializedName("pracownicy_imie_nazwisko") val pracownicy_imie_nazwisko : String
)