package com.klinovvlad.task1klinov.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.Adapter.MainAdapter
import com.klinovvlad.task1klinov.Model.Item
import com.klinovvlad.task1klinov.databinding.FirstScreenBinding

class FirstScreen : Fragment() {
    private lateinit var first_screen_binding: FirstScreenBinding
    lateinit var _adapter: MainAdapter
    lateinit var linerLayoutManager: LinearLayoutManager
    private var item_list_main: ArrayList<Item> = ArrayList()
    val char_list = arrayOf('a'..'z')

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        first_screen_binding = FirstScreenBinding.inflate(inflater, container, false)
        return first_screen_binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(first_screen_binding) {
        super.onViewCreated(view, savedInstanceState)
        val id_list = listOf(
            Item(0, "name", "description"),
            Item(1, "name1", "description1")
        )
        recyclerviewMain.apply {
            linerLayoutManager = LinearLayoutManager(activity)
            recyclerviewMain.layoutManager = linerLayoutManager
            recyclerviewMain.setHasFixedSize(true)
            item_list_main.addAll(id_list)
            _adapter = MainAdapter(item_list_main)
            _adapter.notifyDataSetChanged()
            recyclerviewMain.adapter = _adapter
        }

    }

    companion object {
        fun newInstance() = FirstScreen()
    }
}