package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.Item

interface FirstScreenView {

    fun showItems(items: List<Item>)

    fun saveId(id: Int): Int

}