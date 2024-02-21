package ru.com.vbulat.servicetest

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {

    val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        log("onDestroy")
    }

    override fun onStartCommand(intent : Intent?, flags : Int, startId : Int) : Int {
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 0..100){
                delay(1000)
                log("Timer: $i")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun log(message : String) {
        Log.d("SERVICE.TAG", "MyService: $message")
    }

    override fun onBind(p0 : Intent?) : IBinder? {
        TODO("Not jet implemented")
    }

    companion object{
        fun newIntent(context : Context) : Intent {

            return Intent(context, MyService::class.java)
        }
    }
}