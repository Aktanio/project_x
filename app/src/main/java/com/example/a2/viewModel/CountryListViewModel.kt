package com.example.a2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2.data.CountryEntity
import com.example.a2.data.db.AppError
import com.example.a2.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository<CountryEntity>
): ViewModel() {
    private val _countriesListLiveData = MutableLiveData<List<CountryEntity>>()
    val countriesLiveData: LiveData<List<CountryEntity>> = _countriesListLiveData

    private val _errorSharedFlow = MutableSharedFlow<AppError>(replay = 0)
    val errorSharedFlow: SharedFlow<AppError> = _errorSharedFlow

    fun requestAllCountries() = viewModelScope.launch{

        try {
            val cachedCountries = loadCachedCountries()
            if (cachedCountries.isNotEmpty()){
                _countriesListLiveData.value = cachedCountries
            }else{
                val apiCountries = countryRepository.getAllCountries()

                countryRepository.insertAllCountries(apiCountries)

                _countriesListLiveData.value = loadCachedCountries()
            }
        }catch (e: Exception){
            if (loadCachedCountries().isEmpty()){
                _errorSharedFlow.emit(AppError.NoDataError)
            }else{
                _errorSharedFlow.emit(AppError.PartialDataError)
            }
        }
    }
    private suspend fun loadCachedCountries() : List<CountryEntity> {
        return countryRepository.getCachedCountries()
    }
}