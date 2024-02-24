package ru.com.vbulat.servicetest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {

    @Deprecated("Deprecated in Java")
    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        log("onCreate")
        super.onCreate()
    }

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

    override fun onHandleWork(intent: Intent) {
        log("onHandleWork")
        val page = intent.getIntExtra(PAGE, 0)
        for (i in 0..10) {
            Thread.sleep(1000)
            log("Timer: $i $page")
        }
    }

    private fun log(message: String) {
        Log.d("SERVICE.TAG", "MyJobIntentService: $message")
    }

    companion object {

        private const val PAGE = "page"
        private const val JOB_ID = 201

        fun enqueue(context: Context, page: Int){
            enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context, page),
            )
        }

        private fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}