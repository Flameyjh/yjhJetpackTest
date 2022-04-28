package com.yjh.yjhjetpacktest.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yjh.yjhjetpacktest.R

/*
* 2. Lifecycles: 时刻感知Activity的生命周期。
* */
class LifecyclesActivity : AppCompatActivity() {

    lateinit var observer: MyObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycles)

        lifecycle.addObserver(MyObserver(lifecycle))
    }
}