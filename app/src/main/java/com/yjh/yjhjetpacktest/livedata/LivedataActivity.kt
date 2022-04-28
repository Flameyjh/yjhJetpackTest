package com.yjh.yjhjetpacktest.livedata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yjh.yjhjetpacktest.R

/*
* 3. LiveData: 数据发生变化时通知给观察者。因为点击按钮立即刷新UI的方式在耗时请求时不适合。
*    Map和SwitchMap方法：
* */
class LivedataActivity : AppCompatActivity() {

    lateinit var vm: MyViewModel
    lateinit var sp: SharedPreferences
    val KEY = "counter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(KEY, 0)
        vm = ViewModelProviders.of(this, MyViewModelFactory(countReserved)).get(MyViewModel::class.java)

        findViewById<Button>(R.id.plusOneBtn).setOnClickListener {
            vm.plusOne()
        }
        findViewById<Button>(R.id.clearBtn).setOnClickListener {
            vm.clear()
        }
        findViewById<Button>(R.id.getUserBtn1).setOnClickListener {
            val userId = (0..10000).random().toString()
            vm.getUserLiveData(userId)
        }
        findViewById<Button>(R.id.getUserBtn2).setOnClickListener {
            val userId = (0..10000).random().toString()
            vm.getUser(userId)
        }

        //观察ViewModel里面的LiveData，当数据改变时回调到这里
        vm.counter.observe(this, Observer { count ->
                findViewById<TextView>(R.id.infoText).text = count.toString()
        })
        //或者
//        vm.counter.observe(this) { count ->
//            findViewById<TextView>(R.id.infoText).text = count.toString()
//        }

        //Map
        vm.userName.observe(this, { name ->
            findViewById<TextView>(R.id.infoText).text = name
        })

        //SwitchMap
        vm.user.observe(this, { user ->
            findViewById<TextView>(R.id.infoText).text = user.firstName
        })
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            vm.counter.value?.let { putInt(KEY, it) }
        }
    }

}