package com.klinovvlad.task1klinov.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task1klinov.databinding.ItemMainBinding
import com.klinovvlad.task1klinov.model.Item

class MainAdapter(
    private val itemList: List<Item>,
    private val onItemClickLambda: (item: Item) -> Unit) :
    ListAdapter<Item, MainAdapter.MainHolder>(MainUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(ItemMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(getItem(position))
        val data = itemList[position]
        holder.itemView.setOnClickListener {
            onItemClickLambda(data)
        }
    }

    class MainHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.recycName.text = item.name
        }
        }

    class MainUtil : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }
}

