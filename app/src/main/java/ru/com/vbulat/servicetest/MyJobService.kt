package ru.com.vbulat.servicetest

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyJobService : JobService() {

    val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        log("onCreate")
        super.onCreate()
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        log("onStartCommand")

        coroutineScope.launch {
            for (i in 0 ..  10) {
                delay(1000)
                log("Timer: $i")
            }
            jobFinished(p0, true)
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        log("onStopJob")
        return true
    }

    private fun log(message : String) {
        Log.d("SERVICE.TAG", "MyService: $message")
    }
}