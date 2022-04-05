package com.klinovvlad.task1klinov.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.adapter.MainAdapter
import com.klinovvlad.task1klinov.databinding.FragmentFirstScreenBinding
import com.klinovvlad.task1klinov.model.BUNDLE_ITEM
import com.klinovvlad.task1klinov.model.ID_PREF_KEY
import com.klinovvlad.task1klinov.model.ItemHolder
import com.klinovvlad.task1klinov.model.MAIN_PREF_KEY

class FirstScreen : Fragment() {
    private lateinit var firstScreenBinding: FragmentFirstScreenBinding

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

        createRecycler()
    }

    private fun createRecycler() {
        firstScreenBinding.recyclerviewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            val mainAdapter = MainAdapter {
                val bundle = Bundle()
                bundle.putInt(BUNDLE_ITEM, it.id)
                val secondFragment = SecondScreen()
                secondFragment.arguments = bundle
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.main_frame, secondFragment)
                    ?.addToBackStack(null)
                    ?.commit()
                val sharedPref = activity?.getSharedPreferences(
                    MAIN_PREF_KEY,
                    Context.MODE_PRIVATE
                )
                sharedPref
                    ?.edit()
                    ?.putInt(ID_PREF_KEY, it.id)
                    ?.apply()
            }
            adapter = mainAdapter
            mainAdapter.submitList(ItemHolder().getItems())
        }
    }
}