package com.example.countries_api.contract

import androidx.fragment.app.Fragment

interface CountriesNavigator {
    fun openCountrySearchFragment(): Fragment
    fun openCountryListFragment(): Fragment
}