package com.klinovvlad.task1klinov.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.view.adapter.MainAdapter
import com.klinovvlad.task1klinov.databinding.FragmentFirstScreenBinding
import com.klinovvlad.task1klinov.utils.BUNDLE_KEY_ID
import com.klinovvlad.task1klinov.utils.MAIN_PREF_KEY
import com.klinovvlad.task1klinov.viewmodel.FirstScreenViewModel
import com.klinovvlad.task1klinov.viewmodel.FirstScreenViewModelFactory

class FirstScreen : Fragment() {
    private lateinit var firstScreenBinding: FragmentFirstScreenBinding
    private val viewModel: FirstScreenViewModel by lazy {
        ViewModelProvider(
            requireActivity(), FirstScreenViewModelFactory(
                requireActivity().getSharedPreferences(
                    MAIN_PREF_KEY,
                    Context.MODE_PRIVATE
                )
            )
        ).get(FirstScreenViewModel::class.java)
    }
    private val mainAdapter: MainAdapter by lazy {
        MainAdapter {
            val bundle = Bundle()
            bundle.putInt(BUNDLE_KEY_ID, it.id)
            val secondFragment = SecondScreen()
            secondFragment.arguments = bundle
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_frame, secondFragment)
                ?.addToBackStack(null)
                ?.commit()
            viewModel.saveId(it.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firstScreenBinding = FragmentFirstScreenBinding.inflate(
            inflater,
            container,
            false
        )
        return firstScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        firstScreenBinding.recyclerviewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
        viewModel.itemList.observe(requireActivity()) {
            mainAdapter.submitList(it)
        }
    }
}