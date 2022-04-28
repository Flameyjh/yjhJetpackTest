package com.yjh.yjhjetpacktest.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/*
* 将数字使用LivaData来包装，然后在Activity中去观察它，就可以主动将变化通知给Activity了
* */
class MyViewModel(countReserved: Int):  ViewModel(){

    //Map: 用于把User类型中外部需要的部分暴露给外部，比如外部只需要User中的name属性
    private val userLiveData = MutableLiveData<User>()
    val userName: LiveData<String> = Transformations.map(userLiveData){ user ->
        "${user.firstName} + ${user.lastName}"
    }

    //SwitchMap: 每次Repository.getUser返回的LiveData都是一个新实例，要将它转换成一个可观察的LiveData
    private val userIdLiveData = MutableLiveData<String>()
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    //LiveData和MutableLiveData：给外部一个不可变的LiveData类型，内部处理数据用可变的MutableLiveData类型
    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne(){
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear(){
        _counter.value = 0
    }

    fun getUserLiveData(userId: String){
        userLiveData.value = User(userId, userId, 0)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
}