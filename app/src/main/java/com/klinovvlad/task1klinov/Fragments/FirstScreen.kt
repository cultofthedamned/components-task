package com.klinovvlad.task1klinov.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.Activities.MainActivity
import com.klinovvlad.task1klinov.Activities.SecondActivity
import com.klinovvlad.task1klinov.Adapter.MainAdapter
import com.klinovvlad.task1klinov.Fragments.Communacation.Communicator
import com.klinovvlad.task1klinov.Model.Item
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.FirstScreenBinding

class FirstScreen : Fragment(), MainAdapter.OnItemClickListener {
    private lateinit var first_screen_binding: FirstScreenBinding
    lateinit var _adapter: MainAdapter
    lateinit var linerLayoutManager: LinearLayoutManager
    private var item_list_main: ArrayList<Item> = ArrayList()
    private lateinit var communicator: Communicator
    lateinit var shared_id : SharedPreferences
    lateinit var shared_name : SharedPreferences
    lateinit var shared_description : SharedPreferences

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
        recyclerviewMain.apply {
            linerLayoutManager = LinearLayoutManager(activity)
            recyclerviewMain.layoutManager = linerLayoutManager
            recyclerviewMain.setHasFixedSize(true)
            item_list_main.addAll(id_list)
            _adapter = MainAdapter(item_list_main, this@FirstScreen)
            _adapter.notifyDataSetChanged()
            recyclerviewMain.adapter = _adapter
        }

    }

    override fun onItemClick(position: Int) {
        //val bundle = Bundle()
        //bundle.putString("id", (item_list_main[position].id).toString())
        //bundle.putString("name", item_list_main[position].name)
        //bundle.putString("description", item_list_main[position].description)
        //val second_fragment = SecondScreen()
        //second_fragment.arguments = bundle

        val id_item = (item_list_main[position].id).toString()
        val name_item = item_list_main[position].name
        val description_item = item_list_main[position].description

        shared_id = FragmentActivity().getSharedPreferences("pref_id", Context.MODE_PRIVATE)
        shared_name = FragmentActivity().getSharedPreferences("pref_name", Context.MODE_PRIVATE)
        shared_description = FragmentActivity().getSharedPreferences("pref_description", Context.MODE_PRIVATE)
        var edit_id = shared_id.edit()
        var edit_name = shared_name.edit()
        var edit_description = shared_description.edit()
        edit_id.putString("id_shared", id_item)
        edit_name.putString("name_shared", name_item)
        edit_description.putString("description_shared", description_item)
        edit_id.commit()
        edit_name.commit()
        edit_description.commit()

        //communicator = requireActivity() as Communicator
        //communicator.passDataCom(id_item,
            //name_item,
            //description_item)
        //fragmentManager?.beginTransaction()?.replace(R.id.main_frame, SecondScreen())?.commit()
        activity?.getSupportFragmentManager()
            ?.beginTransaction()
            ?.replace(R.id.main_frame, SecondScreen.newInstance())
            ?.commit()
    }

    companion object {
        fun newInstance() = FirstScreen()
    }
}