package com.example.a2.di

import com.example.a2.fragments.ArtListFragment
import com.example.a2.fragments.ArtSearchFragment
import com.example.a2.fragments.CountriesListFragment
import com.example.a2.fragments.CountriesSearchFragment
import dagger.Component

@Component(modules = [RetrofitModule::class, CountryRepositoryModule::class, ArtRepositoryModule::class])
interface AppComponent {
    fun injectArtSearch(artSearchFragment: ArtSearchFragment)
    fun injectArtList(artListFragment: ArtListFragment)
    fun injectCountrySearch(countriesSearchFragment: CountriesSearchFragment)
    fun injectCountryList(countriesListFragment: CountriesListFragment)
}