package ru.com.vbulat.servicetest

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyForegroundService : Service() {

    val coroutineScope = CoroutineScope(Dispatchers.Main)

    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        log("onCreate")
        super.onCreate()

        val notificationManager = createNotificationChanel()
        val notification = createNotification()

        notificationManager.notify(NOTIFICATION_ID, notification)

        startForeground(NOTIFICATION_ID, notification)

    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHSNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
    }

    private fun createNotificationChanel(): NotificationManager {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHSNEL_ID,
                CHAMEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        return notificationManager
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onStartCommand(intent : Intent?, flags : Int, startId : Int) : Int {
        log("onStartCommand")

        coroutineScope.launch {
            for (i in 0 .. 100) {
                delay(1000)
                log("Timer: $i")
            }
        }
        return START_STICKY
    }

    private fun log(message : String) {
        Log.d("SERVICE.TAG", "MyForegroundService: $message")
    }

    override fun onBind(p0 : Intent?) : IBinder? {
        TODO("Not jet implemented")
    }

    companion object {

        private const val CHSNEL_ID = "chanel_id"
        private const val CHAMEL_NAME = "chanel_name"

        private const val NOTIFICATION_ID = 1

        fun newIntent(context : Context) : Intent {
            return Intent(context, MyForegroundService::class.java)
        }
    }
}