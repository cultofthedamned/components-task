package com.klinovvlad.task1klinov.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding
import com.klinovvlad.task1klinov.fragments.FirstScreen
import com.klinovvlad.task1klinov.fragments.SecondScreen
import com.klinovvlad.task1klinov.service.MainService

class MainActivity : AppCompatActivity()     {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainService.startService(this)

        val idExtra = intent.getIntExtra("idExtraKey", 0)

        if (idExtra != 0) {
            val bundle = Bundle()
            bundle.putInt("item", idExtra)
            val secondFragment = SecondScreen()
            secondFragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, FirstScreen())
                .addToBackStack(null)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, secondFragment)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, FirstScreen())
                .addToBackStack(null)
                .commit()
        }
    }
}