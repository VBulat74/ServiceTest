package ru.com.vbulat.servicetest

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class MyIntentService : IntentService(NAME_SERVICE) {

    @Deprecated("Deprecated in Java")
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

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(p0 : Intent?) {
        log("onHandleIntent")
        for (i in 0 .. 10) {
            Thread.sleep(1000)
            log("Timer: $i")
        }
    }

    private fun log(message : String) {
        Log.d("SERVICE.TAG", "MyForegroundService: $message")
    }

    companion object {

        private const val CHSNEL_ID = "chanel_id"
        private const val CHAMEL_NAME = "chanel_name"
        private const val NOTIFICATION_ID = 1

        private const val NAME_SERVICE = "MyIntentService"

        fun newIntent(context : Context) : Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}