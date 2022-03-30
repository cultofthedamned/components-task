package com.klinovvlad.task1klinov.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klinovvlad.task1klinov.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var bining: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bining = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bining.root)

        val sharedPref = getSharedPreferences("mainPref", Context.MODE_PRIVATE)
        bining.textSecond1.text = "id: " + sharedPref.getString("id", null)
        bining.textSecond2.text = "name: " + sharedPref.getString("name", null)
        bining.textSecond3.text = "description: " + sharedPref.getString("description", null)
    }
}