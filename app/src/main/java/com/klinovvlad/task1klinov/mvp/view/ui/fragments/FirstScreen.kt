package com.klinovvlad.task1klinov.mvp.view.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.mvp.view.adapter.MainAdapter
import com.klinovvlad.task1klinov.databinding.FragmentFirstScreenBinding
import com.klinovvlad.task1klinov.mvp.model.Item
import com.klinovvlad.task1klinov.mvp.presenter.FirstScreenPresenter
import com.klinovvlad.task1klinov.mvp.presenter.FirstScreenView
import com.klinovvlad.task1klinov.utils.BUNDLE_KEY_ID
import com.klinovvlad.task1klinov.utils.MAIN_PREF_KEY

class FirstScreen : Fragment(), FirstScreenView {
    private lateinit var firstScreenBinding: FragmentFirstScreenBinding
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
            firstScreenPresenter.callSaveId(it.id)
        }
    }
    private val firstScreenPresenter: FirstScreenPresenter by lazy {
        FirstScreenPresenter(
            requireActivity().getSharedPreferences(
                MAIN_PREF_KEY,
                Context.MODE_PRIVATE
            )
        )
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
        firstScreenPresenter
        return firstScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstScreenPresenter.attachView(this)
        firstScreenBinding.recyclerviewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = mainAdapter
            firstScreenPresenter.callData()
        }
    }

    override fun onDestroyView() {
        firstScreenPresenter.detachView()
        super.onDestroyView()
    }

    override fun showItems(items: List<Item>) {
        mainAdapter.submitList(items)
    }
}