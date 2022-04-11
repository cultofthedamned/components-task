package com.klinovvlad.task1klinov.viewmodel

import androidx.lifecycle.ViewModel
import com.klinovvlad.task1klinov.model.Item
import com.klinovvlad.task1klinov.model.ItemHolder

class SecondScreenViewModel : ViewModel() {

    fun showItem(data: Int) : Item? {
        return ItemHolder().receiveItem(data)
    }

}