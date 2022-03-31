package com.klinovvlad.task1klinov.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding
import com.klinovvlad.task1klinov.fragments.FirstScreen
import com.klinovvlad.task1klinov.fragments.SecondScreen
import com.klinovvlad.task1klinov.fragments.communicator.Communicator
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

    override fun onDestroy() {
        val sharedPref = getSharedPreferences("mainPref", Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .clear()
            .commit()
        super.onDestroy()
    }

    override fun sendData(id: Int, name: String, description: String) {
        val bundle = Bundle()
        bundle.putString("id", id.toString())
        bundle.putString("name", name)
        bundle.putString("description", description)
        val secondFragment = SecondScreen()
        secondFragment.arguments = bundle
        val sharedPref = getSharedPreferences("mainPref", Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .putString("idPref", bundle.getString("id"))
            .commit()
        this.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, secondFragment)
            .addToBackStack(null)
            .commit()

    }
}