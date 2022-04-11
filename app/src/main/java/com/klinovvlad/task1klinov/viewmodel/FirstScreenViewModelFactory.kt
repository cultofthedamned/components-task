package com.klinovvlad.task1klinov.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FirstScreenViewModelFactory(private val sharedPref: SharedPreferences) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FirstScreenViewModel::class.java)) {
            return FirstScreenViewModel(this.sharedPref) as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}