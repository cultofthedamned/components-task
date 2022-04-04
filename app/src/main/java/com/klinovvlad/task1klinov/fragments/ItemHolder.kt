package com.klinovvlad.task1klinov.fragments

import com.klinovvlad.task1klinov.model.Item

class ItemHolder {

    private val items = (0 until 20).map {
            i -> Item(i, "name $i", "description $i")
    }

    fun returnItemList(): List<Item> {
        return items
    }

    fun receiveItem(clickedItemPosition: Int): Item? {
        return items.find { it.id == clickedItemPosition }
    }
}