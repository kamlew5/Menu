package com.example.menu.serializers

import com.google.gson.annotations.SerializedName

data class SubExamList (
    @SerializedName("id") val exam_id : Int,
    @SerializedName("pacjent_imie_nazwisko") val pacjent_imie_nazwisko : String,
    @SerializedName("pracownicy_imie_nazwisko") val pracownicy_imie_nazwisko : String,
    @SerializedName("spis_zabiegi_nazwa") val spis_zabiegi_nazwa : String
)