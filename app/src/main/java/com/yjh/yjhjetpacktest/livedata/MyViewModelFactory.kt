package com.yjh.yjhjetpacktest.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(private val countReserved: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(countReserved) as T
    }

}