package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.Item

interface ItemView {

    fun showItemView(items: List<Item>)

}