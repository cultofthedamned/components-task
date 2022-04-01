package com.klinovvlad.task1klinov.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task1klinov.adapter.MainAdapter
import com.klinovvlad.task1klinov.databinding.FragmentFirstScreenBinding
import com.klinovvlad.task1klinov.fragments.communicator.Communicator
import com.klinovvlad.task1klinov.model.Item

class FirstScreen : Fragment() {
    private lateinit var firstScreenBinding: FragmentFirstScreenBinding
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

    companion object ItemHolder {

        private val items = (0 until 20).map {
                i -> Item(i, "name" + i, "description" + i)
        }

        private fun returnItemList(): List<Item> {
            return items
        }

        fun receiveItem(clickedItemPosition: Int): String {
            val currentItem = items.get(clickedItemPosition).toString()
            return currentItem
        }
    }

    private fun createRecycler() {
        var communicator: Communicator
        firstScreenBinding.recyclerviewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            listMain.addAll(returnItemList())
            val mainAdapter = MainAdapter {
                communicator = requireActivity() as Communicator
                communicator.onItemClicked(it.id)
            }
            adapter = mainAdapter
            mainAdapter.submitList(listMain)
        }
    }
}