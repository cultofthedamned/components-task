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
import com.klinovvlad.task1klinov.utils.PREF_KEY_ID
import com.klinovvlad.task1klinov.utils.MAIN_PREF_KEY

class FirstScreen : Fragment(), FirstScreenView {
    private lateinit var firstScreenBinding: FragmentFirstScreenBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var firstScreenPresenter: FirstScreenPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firstScreenBinding = FragmentFirstScreenBinding.inflate(
            inflater,
            container,
            false
        )
        firstScreenPresenter = FirstScreenPresenter()
        return firstScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstScreenPresenter.attachView(this)
        firstScreenBinding.recyclerviewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            mainAdapter = MainAdapter {
                val bundle = Bundle()
                bundle.putInt(BUNDLE_KEY_ID, it.id)
                val secondFragment = SecondScreen()
                secondFragment.arguments = bundle
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.main_frame, secondFragment)
                    ?.addToBackStack(null)
                    ?.commit()
                val sharedPref = activity?.getSharedPreferences(MAIN_PREF_KEY, Context.MODE_PRIVATE)
                sharedPref
                    ?.edit()
                    ?.putInt(PREF_KEY_ID, firstScreenPresenter.callSaveId(it.id))
                    ?.apply()
            }
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

    override fun saveId(id: Int): Int {
        return id
    }
}