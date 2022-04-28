package com.yjh.yjhjetpacktest.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0) //简单模拟一下像后端发请求要数据
        return liveData
    }
}