package com.yjh.yjhjetpacktest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProviders
import com.yjh.yjhjetpacktest.lifecycles.LifecyclesActivity
import com.yjh.yjhjetpacktest.lifecycles.MyObserver
import com.yjh.yjhjetpacktest.livedata.LivedataActivity
import com.yjh.yjhjetpacktest.room.RoomActivity
import com.yjh.yjhjetpacktest.viewmodel.MainVewModel
import com.yjh.yjhjetpacktest.viewmodel.MainViewModelFactory
import com.yjh.yjhjetpacktest.viewmodel.ViewModelActivity
import com.yjh.yjhjetpacktest.workmanager.WorkManagerActivity

/*
* Jetpack的基本使用
* 1. ViewModel：ViewModel的基本用法，向ViewModel传递参数。
* 2. Lifecycles: 时刻感知Activity的生命周期。
* 3. LiveData: 数据发生变化时(ViewModel中的LiveData)通知给观察者(Activity)。因为点击按钮立即刷新UI的方式在耗时请求时不适合。
* 4. Room: 使用Room三角色增删改查，Room的数据库升级。
* 5. WorkManager: 后台任务，处理定时任务的工具，即使手机重启，之前注册的任务仍会执行(比如定期和服务器同步数据)。
* */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.viewModelBtn).setOnClickListener {
            startActivity(Intent(this, ViewModelActivity::class.java))
        }

        findViewById<Button>(R.id.lifecyclesBtn).setOnClickListener {
            startActivity(Intent(this, LifecyclesActivity::class.java))
        }

        findViewById<Button>(R.id.liveDataBtn).setOnClickListener {
            startActivity(Intent(this, LivedataActivity::class.java))
        }

        findViewById<Button>(R.id.roomBtn).setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }

        findViewById<Button>(R.id.workManagerBtn).setOnClickListener {
            startActivity(Intent(this, WorkManagerActivity::class.java))
        }

    }


}