package com.klinovvlad.task1klinov.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding
import com.klinovvlad.task1klinov.fragments.FirstScreen
import com.klinovvlad.task1klinov.fragments.SecondScreen
import com.klinovvlad.task1klinov.model.BUNDLE_ITEM
import com.klinovvlad.task1klinov.model.TWENTY
import com.klinovvlad.task1klinov.model.ID_EXTRA_KEY
import com.klinovvlad.task1klinov.service.MainService

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

        val idExtra = intent.getIntExtra(ID_EXTRA_KEY, TWENTY)

        if (idExtra != TWENTY) {
            val bundle = Bundle()
            bundle.putInt(BUNDLE_ITEM, idExtra)
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