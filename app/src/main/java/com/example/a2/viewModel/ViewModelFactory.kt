package com.example.a2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2.repository.ArtRepository
import com.example.a2.repository.CountryRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val artRepository: ArtRepository,
    private val countryRepository: CountryRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(ArtSearchViewModel::class.java)->{
                modelClass.getConstructor(ArtRepository::class.java).newInstance(artRepository)
            }
            modelClass.isAssignableFrom(ArtListViewModel::class.java)->{
                modelClass.getConstructor(ArtRepository::class.java).newInstance(artRepository)
            }
            modelClass.isAssignableFrom(CountrySearchViewModel::class.java)->{
                modelClass.getConstructor(CountryRepository::class.java).newInstance(countryRepository)
            }
            modelClass.isAssignableFrom(CountryListViewModel::class.java)->{
                modelClass.getConstructor(CountryRepository::class.java).newInstance(countryRepository)
            }
            else -> throw IllegalArgumentException("Неизвестный viewModel class: ${modelClass.name}")
        }
    }
}