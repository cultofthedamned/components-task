package com.klinovvlad.task1klinov.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.adapter.MainAdapter
import com.klinovvlad.task1klinov.databinding.FragmentFirstScreenBinding
import com.klinovvlad.task1klinov.fragments.communicator.Communicator
import com.klinovvlad.task1klinov.model.Item

class FirstScreen : Fragment() {
    private lateinit var firstScreenBinding: FragmentFirstScreenBinding
    private lateinit var communicator: Communicator
    private var listMain: ArrayList<Item> = ArrayList()

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

    override fun onDestroyView() {
        listMain.clear()
        super.onDestroyView()
    }

    private fun createRecycler() {
        val dataList = arrayListOf<Item>()
        for (i in 0..19) {
            dataList.add(Item(i, "name" + i, "description" + i))
        }
        firstScreenBinding.recyclerviewMain.apply {
            firstScreenBinding.recyclerviewMain.layoutManager = LinearLayoutManager(activity)
            firstScreenBinding.recyclerviewMain.setHasFixedSize(true)
            listMain.addAll(dataList)
            val adapter = MainAdapter {
                communicator = requireActivity() as Communicator
                communicator.sendData(
                    it.id,
                    it.name,
                    it.description
                )
            }
            adapter.submitList(listMain)
            firstScreenBinding.recyclerviewMain.adapter = adapter
        }
    }


}