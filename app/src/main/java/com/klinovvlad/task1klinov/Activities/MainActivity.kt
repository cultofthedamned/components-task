package com.klinovvlad.task1klinov.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.Adapter.MainAdapter
import com.klinovvlad.task1klinov.Fragments.Communacation.Communicator
import com.klinovvlad.task1klinov.Fragments.FirstScreen
import com.klinovvlad.task1klinov.Fragments.SecondScreen
import com.klinovvlad.task1klinov.Model.Item
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.Service.MainService
import com.klinovvlad.task1klinov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var shared_id : SharedPreferences
    lateinit var shared_name : SharedPreferences
    lateinit var shared_description : SharedPreferences
    lateinit var _adapter: MainAdapter
    lateinit var linerLayoutManager: LinearLayoutManager
    private var item_list_main: ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainService.startService(this, "Some text >_<")

        setDataToRecyclerView()

        //supportFragmentManager
            //.beginTransaction()
            //.replace(R.id.main_frame, FirstScreen.newInstance())
            //.commit()
    }

    fun setDataToRecyclerView() = with(binding) {
        val id_list = listOf(
            Item(0, "name0", "description0"),
            Item(1, "name1", "description1"),
            Item(2, "name2", "description2"),
            Item(3, "name3", "description3"),
            Item(4, "name4", "description4"),
            Item(5, "name5", "description5"),
            Item(6, "name6", "description6"),
            Item(7, "name7", "description7"),
            Item(8, "name8", "description8"),
            Item(9, "name9", "description9"),
            Item(10, "name10", "description10"),
            Item(11, "name11", "description11"),
            Item(12, "name12", "description12"),
            Item(13, "name13", "description13"),
            Item(14, "name14", "description14"),
            Item(15, "name15", "description15"),
            Item(16, "name16", "description16"),
            Item(17, "name17", "description17"),
            Item(18, "name18", "description18"),
            Item(19, "name19", "description19")
        )
        linerLayoutManager = LinearLayoutManager(this@MainActivity)
        recyc.layoutManager = linerLayoutManager
        recyc.setHasFixedSize(true)
        item_list_main.addAll(id_list)
        _adapter = MainAdapter(item_list_main, this@MainActivity)
        _adapter.notifyDataSetChanged()
        recyc.adapter = _adapter
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        shared_id = getSharedPreferences("pref_id", Context.MODE_PRIVATE)
        shared_name = getSharedPreferences("pref_name", Context.MODE_PRIVATE)
        shared_description = getSharedPreferences("pref_description", Context.MODE_PRIVATE)
        var edit_id = shared_id.edit()
        var edit_name = shared_name.edit()
        var edit_description = shared_description.edit()
        edit_id.putString("id_shared", (item_list_main[position].id).toString())
        edit_name.putString("name_shared", item_list_main[position].name)
        edit_description.putString("description_shared", item_list_main[position].description)
        edit_id.commit()
        edit_name.commit()
        edit_description.commit()
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        MainService.stopService(this)
        shared_id = getSharedPreferences("pref_id", Context.MODE_PRIVATE)
        shared_name = getSharedPreferences("pref_name", Context.MODE_PRIVATE)
        shared_description = getSharedPreferences("pref_description", Context.MODE_PRIVATE)
        val edit_id = shared_id.edit()
        val edit_name = shared_name.edit()
        val edit_description = shared_description.edit()
        edit_id.clear().commit()
        edit_name.clear().commit()
        edit_description.clear().commit()
    }


    fun passDataCom(txt1: String, txt2: String, txt3: String) {
        val bundle = Bundle()
        bundle.putString("id", txt1)
        bundle.putString("name", txt2)
        bundle.putString("description", txt3)
        val transaction = this.supportFragmentManager.beginTransaction()
        val second_fragment = SecondScreen()
        second_fragment.arguments = bundle
        transaction.replace(R.id.main_frame, SecondScreen())
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}