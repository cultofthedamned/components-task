package com.klinovvlad.task1klinov.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task1klinov.databinding.ItemMainBinding
import com.klinovvlad.task1klinov.model.Item

class MainAdapter : ListAdapter<Item, MainAdapter.TestHolder>(TestUtil()) {
    private lateinit var clickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        return TestHolder(ItemMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false),
            clickListener)
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TestHolder(private val binding: ItemMainBinding,
                     private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.recycName.text = item.name
        }
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        }

    class TestUtil : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }

    interface OnItemClickListener {

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }
}

