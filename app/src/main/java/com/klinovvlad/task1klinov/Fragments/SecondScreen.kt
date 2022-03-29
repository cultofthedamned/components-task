package com.klinovvlad.task1klinov.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentResultListener
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.SecondScreenBinding

class SecondScreen : Fragment() {
    private lateinit var second_screen_binding: SecondScreenBinding
    var id_string: String? = null
    var name_string: String? = null
    var description_string: String? = null

    lateinit var shared_id : SharedPreferences
    lateinit var shared_name : SharedPreferences
    lateinit var shared_description : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        second_screen_binding = SecondScreenBinding.inflate(inflater, container, false)

        shared_id = FragmentActivity().getSharedPreferences("pref_id", Context.MODE_PRIVATE)
        shared_name = FragmentActivity().getSharedPreferences("pref_name", Context.MODE_PRIVATE)
        shared_description = FragmentActivity().getSharedPreferences("pref_description", Context.MODE_PRIVATE)
        second_screen_binding.textViewSecond1.text = shared_id.getString("id_shared", "")


        //Toast.makeText(activity, arguments?.getString("name"), Toast.LENGTH_SHORT).show()

        //id_string = arguments?.getString("id")
        //name_string = arguments?.getString("name")
        //description_string = arguments?.getString("description")
        //second_screen_binding.textViewSecond1.text = id_string
        //second_screen_binding.textViewSecond2.text = name_string
        //second_screen_binding.textViewSecond3.text = description_string

        //val args = this.arguments
        //val id_data = args?.getString("id")
        //val name_data = args?.getString("name")
        //val description_data = args?.getString("description")
        //second_screen_binding.textViewSecond1.text = id_data.toString()
        //second_screen_binding.textViewSecond2.text = name_data.toString()
        //second_screen_binding.textViewSecond3.text = description_data.toString()
        return second_screen_binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = SecondScreen()
    }
}