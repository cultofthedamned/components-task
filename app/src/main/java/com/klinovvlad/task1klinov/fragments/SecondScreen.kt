package com.klinovvlad.task1klinov.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klinovvlad.task1klinov.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
    private lateinit var second_screen_binding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        second_screen_binding = FragmentSecondScreenBinding.inflate(
            inflater,
            container,
            false)

        second_screen_binding.textViewSecond1.text = arguments?.getString("id")
        second_screen_binding.textViewSecond2.text = arguments?.getString("name")
        second_screen_binding.textViewSecond3.text = arguments?.getString("description")

        return second_screen_binding.root
    }
}