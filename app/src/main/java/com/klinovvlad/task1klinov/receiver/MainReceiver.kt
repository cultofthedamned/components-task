package com.klinovvlad.task1klinov.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.klinovvlad.task1klinov.activities.MainActivity
import com.klinovvlad.task1klinov.model.TWENTY
import com.klinovvlad.task1klinov.model.ID_EXTRA_KEY
import com.klinovvlad.task1klinov.model.ID_PREF_KEY
import com.klinovvlad.task1klinov.model.MAIN_PREF_KEY

class MainReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val sharedPref = context.getSharedPreferences(MAIN_PREF_KEY, Context.MODE_PRIVATE)
        val idPref = sharedPref.getInt(ID_PREF_KEY, TWENTY)
        val lastItemIntent = Intent(context, MainActivity::class.java)
        lastItemIntent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
        lastItemIntent.putExtra(ID_EXTRA_KEY, idPref)
        context.startActivity(lastItemIntent)
    }
}
