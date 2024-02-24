package ru.com.vbulat.servicetest

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log

class MyIntentService2 : IntentService(NAME_SERVICE) {

    @Deprecated("Deprecated in Java")
    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        log("onCreate")
        super.onCreate()

        setIntentRedelivery(true)

    }

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        val page = p0?.getIntExtra(PAGE,0) ?: 0
        for (i in 0..10) {
            Thread.sleep(1000)
            log("Timer: $i $page")
        }
    }

    private fun log(message: String) {
        Log.d("SERVICE.TAG", "MyForegroundService: $message")
    }

    companion object {

        private const val NAME_SERVICE = "MyIntentService"
        private const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentService2::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}