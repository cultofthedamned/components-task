package com.klinovvlad.task1klinov.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding
import com.klinovvlad.task1klinov.fragments.FirstScreen
import com.klinovvlad.task1klinov.fragments.ItemHolder
import com.klinovvlad.task1klinov.fragments.SecondScreen
import com.klinovvlad.task1klinov.fragments.communicator.Communicator
import com.klinovvlad.task1klinov.model.Item
import com.klinovvlad.task1klinov.service.MainService

class MainActivity : AppCompatActivity(), Communicator {
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
    }

    override fun onItemClicked(item: Int) {
        val bundle = Bundle()
        bundle.putInt("item", item)
        val secondFragment = SecondScreen()
        secondFragment.arguments = bundle
        val sharedPref = getSharedPreferences("mainPref", Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putInt("idPref", item)
            .apply()
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, secondFragment)
            .addToBackStack(null)
            .commit()
    }
}