package com.klinovvlad.task1klinov.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding
import com.klinovvlad.task1klinov.fragments.FirstScreen
import com.klinovvlad.task1klinov.fragments.SecondScreen
import com.klinovvlad.task1klinov.service.MainService
import com.klinovvlad.task1klinov.utils.BUNDLE_KEY_ID
import com.klinovvlad.task1klinov.utils.PREF_ID_EXTRA_KEY
import com.klinovvlad.task1klinov.utils.DEFAULT_ID_EXTRA_VALUE

class MainActivity : AppCompatActivity()     {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainService.startService(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, FirstScreen())
            .addToBackStack(null)
            .commit()

        val idExtra = intent.getIntExtra(PREF_ID_EXTRA_KEY, DEFAULT_ID_EXTRA_VALUE)

        if (idExtra != DEFAULT_ID_EXTRA_VALUE) {
            val bundle = Bundle()
            bundle.putInt(BUNDLE_KEY_ID, idExtra)
            val secondFragment = SecondScreen()
            secondFragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, secondFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}