package com.klinovvlad.task1klinov.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klinovvlad.task1klinov.model.Item
import com.klinovvlad.task1klinov.model.ItemHolder

class SecondScreenViewModel(private val id: Int) : ViewModel() {

    val item = MutableLiveData<Item>()

    fun showItem() {
        item.postValue(ItemHolder.receiveItem(id))
    }

}