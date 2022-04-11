package com.klinovvlad.task1klinov.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SecondScreenViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return SecondScreenViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}