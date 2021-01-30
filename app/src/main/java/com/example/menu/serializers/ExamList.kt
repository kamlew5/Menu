package com.example.menu.serializers

import com.google.gson.annotations.SerializedName

data class ExamList (
    @SerializedName("data") val data : List<SubExamList>
)