package com.yjh.yjhjetpacktest.lifecycles

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {

    //通过注解感知生命周期
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart(){
        Log.d("MyObserver", "activityStart " + lifecycle.currentState.toString())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop(){
        Log.d("MyObserver", "activityStop "+ lifecycle.currentState.toString())
    }
}