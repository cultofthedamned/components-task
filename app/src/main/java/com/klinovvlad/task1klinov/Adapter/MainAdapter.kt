package com.klinovvlad.task1klinov.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task1klinov.Model.Item
import com.klinovvlad.task1klinov.R
import com.klinovvlad.task1klinov.databinding.ItemMainBinding

class MainAdapter(val item_list: List<Item>, val listener: OnItemClickListener) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    interface OnItemClickListener {

        fun onItemClick(position: Int)

    }

    inner class MainHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        val adapter_binding = ItemMainBinding.bind(item)
        var txt_name: TextView
        init {
            txt_name = adapter_binding.recycName
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val adapter_data = item_list[position]
        holder.txt_name.text = adapter_data.name
    }

    override fun getItemCount(): Int {
        return item_list.size
    }
}