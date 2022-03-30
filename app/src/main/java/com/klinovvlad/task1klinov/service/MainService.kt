package com.klinovvlad.task1klinov.service

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.klinovvlad.task1klinov.activities.MainActivity
import com.klinovvlad.task1klinov.R

const val CHANNEL_ID = "ForegroundService"
class MainService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
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

    // запуск и остановка сервиса
    companion object {
        fun startService(context: Context) {
            val startIntent = Intent(context, MainService::class.java)
            ContextCompat.startForegroundService(context, startIntent)
        }
        fun stopService(context: Context) {
            val stopIntent = Intent(context, MainService::class.java)
            context.stopService(stopIntent)
        }
    }
}