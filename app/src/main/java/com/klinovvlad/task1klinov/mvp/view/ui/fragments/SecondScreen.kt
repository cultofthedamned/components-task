package com.klinovvlad.task1klinov.mvp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klinovvlad.task1klinov.databinding.FragmentSecondScreenBinding
import com.klinovvlad.task1klinov.mvp.model.Item
import com.klinovvlad.task1klinov.mvp.presenter.SecondScreenPresenter
import com.klinovvlad.task1klinov.mvp.presenter.SecondView
import com.klinovvlad.task1klinov.utils.BUNDLE_KEY_ID

class SecondScreen : Fragment(), SecondView {
    private lateinit var secondScreenBinding: FragmentSecondScreenBinding
    private lateinit var secondScreenPresenter: SecondScreenPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        secondScreenBinding = FragmentSecondScreenBinding.inflate(
            inflater,
            container,
            false
        )
        secondScreenPresenter = SecondScreenPresenter(this)
        secondScreenPresenter.showItem(requireArguments().getInt(BUNDLE_KEY_ID))
        return secondScreenBinding.root
    }

    override fun getData(item: Item) {
        secondScreenBinding.textViewSecondId.text = item.id.toString()
        secondScreenBinding.textViewSecondName.text = item.name
        secondScreenBinding.textViewSecondDescription.text = item.description
    }

}