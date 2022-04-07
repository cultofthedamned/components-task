package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.ItemHolder

class FirstScreenPresenter {

    private var mainView: FirstScreenView? = null

    fun callData() {
        mainView?.showItems(ItemHolder().getItems())
    }

    fun callSaveId(id: Int) : Int {
        return mainView!!.saveId(ItemHolder().receiveItem(id)!!.id)
    }

    fun attachView(view: FirstScreenView) {
        this.mainView = view
    }

    fun detachView() {
       mainView = null
    }

}