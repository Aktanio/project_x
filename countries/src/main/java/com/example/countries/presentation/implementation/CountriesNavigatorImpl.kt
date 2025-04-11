package com.example.countries.presentation.implementation

import androidx.fragment.app.Fragment
import com.example.countries.presentation.ui.fragment.CountriesListFragment
import com.example.countries.presentation.ui.fragment.CountriesSearchFragment
import com.example.countries_api.contract.CountriesNavigator
import javax.inject.Inject

class CountriesNavigatorImpl @Inject constructor(): CountriesNavigator {
    override fun openCountrySearchFragment(): Fragment {
        return CountriesSearchFragment()
    }

    override fun openCountryListFragment(): Fragment {
        return CountriesListFragment()
    }
}