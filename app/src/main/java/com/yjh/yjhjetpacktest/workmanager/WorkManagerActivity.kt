package com.yjh.yjhjetpacktest.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.yjh.yjhjetpacktest.R
import java.util.concurrent.TimeUnit

/*
* 5. WorkManager: 后台任务，处理定时任务的工具，即使手机重启，之前注册的任务仍会执行(比如定期和服务器同步数据)。
* */
class WorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        findViewById<Button>(R.id.doWorkBtn).setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES) //让后台任务指定延迟时间后运行
                .addTag("simple") //添加标签，可以通过标签取消后台任务
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS) //重新执行任务，重试时间以线性方式延迟
                .build()
            WorkManager.getInstance(this).enqueue(request)

            //对运行结果进行监听
//            WorkManager.getInstance(this)
//                .getWorkInfoByIdLiveData(request.id)
//                .observe(this){ workInfo ->
//                    if (workInfo.state == WorkInfo.State.SUCCEEDED){
//                        Log.d("WorkManagerActivity", "do work succeeded")
//                    }else if (workInfo.state == WorkInfo.State.FAILED){
//                        Log.d("WorkManagerActivity", "do work failed")
//                    }
//                }

            //链式任务：比如实现先同步，再压缩，最后上传的功能
//            WorkManager.getInstance(this)
//                .beginWith(sync)
//                .then(compress)
//                .then(upload)
//                .enqueue()
        }
    }
}