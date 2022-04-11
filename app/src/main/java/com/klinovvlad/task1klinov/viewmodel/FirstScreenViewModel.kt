package com.klinovvlad.task1klinov.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task1klinov.model.Item
import com.klinovvlad.task1klinov.model.ItemHolder
import com.klinovvlad.task1klinov.utils.PREF_KEY_ID

class FirstScreenViewModel(private val sharedPref: SharedPreferences) : ViewModel() {

    val itemList = MutableLiveData<List<Item>>()

    fun getData() {
        itemList.postValue(ItemHolder.getItems())
    }

    fun saveId(id: Int) {
        sharedPref
            .edit()
            .putInt(PREF_KEY_ID, id)
            .apply()
    }

}