package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.ItemHolder

class MainPresenter(private val mainView: ItemView) {

    fun callData() {
        mainView.showItems(ItemHolder().getItems())
    }

}