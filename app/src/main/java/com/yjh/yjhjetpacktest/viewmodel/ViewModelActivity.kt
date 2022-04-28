package com.yjh.yjhjetpacktest.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProviders
import com.yjh.yjhjetpacktest.R

/*
* 1. ViewModel：ViewModel的基本用法，向ViewModel传递参数。
* */
class ViewModelActivity : AppCompatActivity() {

    lateinit var viewModel: MainVewModel
    lateinit var sp: SharedPreferences
    val KEY = "counter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        //sp保证程序退出再重新打开的情况下数据不会丢失
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(KEY, 0)

        //通过ViewModelProviders得到ViewModel的实例。绝对不可以直接创建，否则每次onCreate都会创建一个新的ViewModel实例
        //通过MainViewModelFactory传递参数
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(countReserved)).get(MainVewModel::class.java)

        refreshCounter()

        findViewById<Button>(R.id.plusOneBtn).setOnClickListener {
            viewModel.counter ++
            refreshCounter()
        }

        findViewById<Button>(R.id.clearBtn).setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt(KEY, viewModel.counter)
        }
    }

    private fun refreshCounter(){
        findViewById<TextView>(R.id.infoText).text = viewModel.counter.toString()
    }
}