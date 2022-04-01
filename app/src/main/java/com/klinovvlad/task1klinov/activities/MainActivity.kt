package com.klinovvlad.task1klinov.activities

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding
import com.klinovvlad.task1klinov.fragments.FirstScreen
import com.klinovvlad.task1klinov.fragments.SecondScreen
import com.klinovvlad.task1klinov.fragments.communicator.Communicator
import com.klinovvlad.task1klinov.model.Item
import com.klinovvlad.task1klinov.receiver.MainReceiver
import com.klinovvlad.task1klinov.service.MainService

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainService.startService(this)

        val receiver = MainReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, FirstScreen())
            .addToBackStack(null)
            .commit()
    }

    override fun onStop() {
        super.onStop()
        val receiver = MainReceiver()
        unregisterReceiver(receiver)
    }

    override fun onItemClicked(item: Int) {
        val bundle = Bundle()
        val currentItemPosition = FirstScreen.receiveItem(item)
        bundle.putString("item", currentItemPosition)
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
