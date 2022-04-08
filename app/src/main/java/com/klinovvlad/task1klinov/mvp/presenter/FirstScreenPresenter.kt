package com.klinovvlad.task1klinov.mvp.presenter

import android.content.SharedPreferences
import com.klinovvlad.task1klinov.mvp.model.ItemHolder
import com.klinovvlad.task1klinov.utils.PREF_KEY_ID

class FirstScreenPresenter(private val sharedPref: SharedPreferences) {

    private var mainView: FirstScreenView? = null

    fun callData() {
        mainView?.showItems(ItemHolder().getItems())
    }

    fun callSaveId(id: Int) {
        sharedPref
            .edit()
            .putInt(PREF_KEY_ID, id)
            .apply()
    }

    fun attachView(view: FirstScreenView) {
        this.mainView = view
    }

    fun detachView() {
       mainView = null
    }

}