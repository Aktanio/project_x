package com.example.artworks_api.contract

import androidx.fragment.app.Fragment

interface ArtworksNavigator {
    fun openArtSearchFragment(): Fragment
    fun openArtListFragment(): Fragment
}