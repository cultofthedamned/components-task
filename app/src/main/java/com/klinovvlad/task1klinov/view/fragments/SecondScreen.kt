package com.klinovvlad.task1klinov.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.task1klinov.databinding.FragmentSecondScreenBinding
import com.klinovvlad.task1klinov.utils.BUNDLE_KEY_ID
import com.klinovvlad.task1klinov.viewmodel.SecondScreenViewModel
import com.klinovvlad.task1klinov.viewmodel.SecondScreenViewModelFactory

class SecondScreen : Fragment() {
    private lateinit var secondScreenBinding: FragmentSecondScreenBinding
    private val viewModel: SecondScreenViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            SecondScreenViewModelFactory(requireArguments().getInt(BUNDLE_KEY_ID))
        ).get(
            SecondScreenViewModel::class.java
        )
    }

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
        viewModel.showItem()
        viewModel.item.observe(requireActivity()) {
            secondScreenBinding.textViewSecondId.text = it.id.toString()
            secondScreenBinding.textViewSecondName.text = it.name
            secondScreenBinding.textViewSecondDescription.text = it.description
        }
    }
}