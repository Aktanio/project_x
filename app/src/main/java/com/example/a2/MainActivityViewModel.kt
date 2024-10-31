package com.example.a2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val _mainFragment = MutableLiveData<Boolean>()
    val mainFragment: LiveData<Boolean> = _mainFragment

    init {
        _mainFragment.value = false
    }
    fun setMainFragment(){
        _mainFragment.value = true
    }
}