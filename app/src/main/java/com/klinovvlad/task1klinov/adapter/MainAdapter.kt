package com.klinovvlad.task1klinov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.task1klinov.model.Item
import com.klinovvlad.task1klinov.databinding.ItemMainBinding

class MainAdapter(
    val itemList: List<Item>,
    val listener: OnItemClickListener
    ) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    lateinit var adapterBinding: ItemMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        adapterBinding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(adapterBinding.root)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val adapterData = itemList[position]
        holder.txtName.text = adapterData.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MainHolder(item: View) :
        RecyclerView.ViewHolder(adapterBinding.root),
        View.OnClickListener {
        var txtName: TextView
        init {
            txtName = adapterBinding.recycName
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {

        fun onItemClick(position: Int)

    }
}