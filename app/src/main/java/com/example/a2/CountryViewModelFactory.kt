package com.example.a2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CountryViewModelFactory @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CountryRepository::class.java).newInstance(countryRepository)
    }
}