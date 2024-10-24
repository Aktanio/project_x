package com.example.a2

import dagger.Component

@Component(modules = [RetrofitModule::class, CountryRepositoryModule::class, ArtRepositoryModule::class])
interface AppComponent {
    fun injectArtSearch(artSearchFragment: ArtSearchFragment)
    fun injectArtList(artListFragment: ArtListFragment)
    fun injectCountrySearch(countriesSearchFragment: CountriesSearchFragment)
    fun injectCountryList(countriesListFragment: CountriesListFragment)
}