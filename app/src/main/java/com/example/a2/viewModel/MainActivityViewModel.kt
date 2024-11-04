package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private val _isMainFragmentOpened = MutableLiveData<Boolean>()
    val isMainFragmentOpened: LiveData<Boolean> = _isMainFragmentOpened

    init {
        _isMainFragmentOpened.value = true
    }
    fun onMainFragmentOpened(){
        _isMainFragmentOpened.value = false
    }
}