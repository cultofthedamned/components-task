package com.klinovvlad.task1klinov.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SecondScreenViewModelFactory(private val id: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return SecondScreenViewModel(this.id) as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}