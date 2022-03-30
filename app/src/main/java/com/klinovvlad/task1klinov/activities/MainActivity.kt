package com.klinovvlad.task1klinov.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.adapter.MainAdapter
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding
import com.klinovvlad.task1klinov.service.MainService
import com.klinovvlad.task1klinov.model.Item

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private var itemList: ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainService.startService(this)

        setData()
    }

    override fun onDestroy() {
        MainService.stopService(this)
        val sharedPref = getSharedPreferences("mainPref", Context.MODE_PRIVATE)
        sharedPref
            .edit()
            .clear()
            .commit()
        super.onDestroy()
    }

    // клик по итему из списка
    override fun onItemClick(position: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        val sharedPref = getSharedPreferences("mainPref", Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putString("id", (itemList[position].id).toString())
        edit.putString("name", itemList[position].name)
        edit.putString("description", itemList[position].description)
        edit.commit()
        startActivity(intent)
    }

    // функция заполняющая список
    private fun setData() {
        var i = 0
        var dataList = arrayListOf<Item>()
        while (i < 20) {
            dataList.add(Item(i, "name" + i.toString(), "description" + i.toString()))
            i++
        }
        binding.recyc.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyc.setHasFixedSize(true)
        itemList.addAll(dataList)
        val adapter = MainAdapter(itemList, this@MainActivity)
        adapter.notifyDataSetChanged()
        binding.recyc.adapter = adapter
    }
}