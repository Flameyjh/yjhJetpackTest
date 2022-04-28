package com.yjh.yjhjetpacktest.viewmodel

import androidx.lifecycle.ViewModel

class MainVewModel(countReserved: Int): ViewModel() {
    var counter = countReserved
}