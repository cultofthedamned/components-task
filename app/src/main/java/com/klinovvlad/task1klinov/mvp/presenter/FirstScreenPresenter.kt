package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.ItemHolder

class FirstScreenPresenter(private val mainView: FirstScreenView) {

    fun callData() {
        mainView.showItems(ItemHolder().getItems())
    }

}