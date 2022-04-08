package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.ItemHolder

class SecondScreenPresenter {

    private var secondView: SecondScreenView? = null

    fun showItem(data: Int) {
        ItemHolder().receiveItem(data)?.also { secondView?.showItemDetails(it) }
    }

    fun attachView(view: SecondScreenView) {
        this.secondView = view
    }

    fun detachView() {
        secondView = null
    }

}