package com.klinovvlad.task1klinov.mvp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klinovvlad.task1klinov.databinding.FragmentSecondScreenBinding
import com.klinovvlad.task1klinov.mvp.model.ItemHolder
import com.klinovvlad.task1klinov.utils.BUNDLE_KEY_ID

class SecondScreen : Fragment() {
    private lateinit var secondScreenBinding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        secondScreenBinding = FragmentSecondScreenBinding.inflate(
            inflater,
            container,
            false
        )
        return secondScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = ItemHolder().receiveItem(requireArguments().getInt(BUNDLE_KEY_ID))
        secondScreenBinding.textViewSecondId.text = item?.id.toString()
        secondScreenBinding.textViewSecondName.text = item?.name
        secondScreenBinding.textViewSecondDescription.text = item?.description
    }
}