package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private val _isMainFragmentNotOpened = MutableLiveData<Boolean>()
    val isMainFragmentNotOpened: LiveData<Boolean> = _isMainFragmentNotOpened

    init {
        _isMainFragmentNotOpened.value = true
    }
    fun onMainFragmentOpened(){
        _isMainFragmentNotOpened.value = false
    }
}