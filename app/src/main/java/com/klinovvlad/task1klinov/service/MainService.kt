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
import com.klinovvlad.task1klinov.model.CHANNEL_ID
import com.klinovvlad.task1klinov.model.MAINSERVICE_NOTIFICATIONCHANNEL_NAME
import com.klinovvlad.task1klinov.model.ONE
import com.klinovvlad.task1klinov.model.ZERO
import com.klinovvlad.task1klinov.receiver.MainReceiver

class MainService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainReceiver::class.java)
        createNotificationChannel()
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            ZERO,
            notificationIntent,
            0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.main_service_content_title))
            .setContentText(getString(R.string.main_service_content_text))
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(ONE, notification)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, MAINSERVICE_NOTIFICATIONCHANNEL_NAME,
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