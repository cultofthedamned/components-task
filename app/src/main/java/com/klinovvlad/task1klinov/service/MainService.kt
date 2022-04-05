package com.klinovvlad.task1klinov.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.receiver.MainReceiver

const val CHANNEL_ID = "ForegroundService"

class MainService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainReceiver::class.java)
        createNotificationChannel()
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Сlick on this notification to go to the latest item")
            .setContentText("Just text below")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    companion object {
        fun startService(context: Context) {
            val startIntent = Intent(context, MainService::class.java)
            ContextCompat.startForegroundService(context, startIntent)
        }
    }
}