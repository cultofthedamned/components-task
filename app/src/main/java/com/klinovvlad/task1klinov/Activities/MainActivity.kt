package com.klinovvlad.task1klinov.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.Adapter.MainAdapter
import com.klinovvlad.task1klinov.Fragments.Communacation.Communicator
import com.klinovvlad.task1klinov.Fragments.FirstScreen
import com.klinovvlad.task1klinov.Model.Item
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, FirstScreen.newInstance())
            .commit()
    }

    override fun passDataCom(txt1: String, txt2: String, txt3: String) {
        val bundle = Bundle()

    }
}