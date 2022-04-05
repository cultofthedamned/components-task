package com.klinovvlad.task1klinov.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.klinovvlad.task1klinov.activities.MainActivity

class MainReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val sharedPref = context.getSharedPreferences("mainPrefKey", Context.MODE_PRIVATE)
        val idPref = sharedPref.getInt("idPrefKey", 0)
        val lastItemIntent = Intent(context, MainActivity::class.java)
        lastItemIntent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
        lastItemIntent.putExtra("idExtraKey", idPref)
        context.startActivity(lastItemIntent)
    }
}
