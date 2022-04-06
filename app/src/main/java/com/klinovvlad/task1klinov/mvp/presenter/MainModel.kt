package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.Item
import com.klinovvlad.task1klinov.mvp.model.ItemHolder

class MainModel(private val fetchItems: FetchItems) : ItemModel {

    override fun getItems() {
        fetchItems.showItems(ItemHolder().getItems())
    }

    interface FetchItems {
        fun showItems(data: List<Item>)
    }

}