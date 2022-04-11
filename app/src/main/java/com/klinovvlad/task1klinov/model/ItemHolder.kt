package com.klinovvlad.task1klinov.model

object ItemHolder {

    private val items = (0 until 20).map { i ->
        Item(i, "name $i", "description $i")
    }

    fun getItems(): List<Item> {
        return items
    }

    fun receiveItem(elementId: Int): Item? {
        return items.find { it.id == elementId }
    }
}