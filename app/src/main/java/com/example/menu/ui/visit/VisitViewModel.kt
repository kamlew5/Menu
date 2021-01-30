package com.example.menu.ui.visit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VisitViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
//        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}