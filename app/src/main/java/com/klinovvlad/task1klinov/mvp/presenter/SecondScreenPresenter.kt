package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.ItemHolder

class SecondScreenPresenter(private val secondView: SecondScreenView) {

    fun showItem(data: Int) {
        secondView.getData(ItemHolder().receiveItem(data)!!)
    }

}