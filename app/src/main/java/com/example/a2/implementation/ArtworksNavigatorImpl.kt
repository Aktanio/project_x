package com.example.a2.implementation

import androidx.fragment.app.Fragment
import com.example.artworks.presentation.ui.fragment.ArtListFragment
import com.example.artworks.presentation.ui.fragment.ArtSearchFragment
import com.example.artworks_api.contract.ArtworksNavigator
import javax.inject.Inject

class ArtworksNavigatorImpl @Inject constructor(): ArtworksNavigator {
    override fun openArtSearchFragment(): Fragment {
        return ArtSearchFragment()
    }

    override fun openArtListFragment(): Fragment {
        return ArtListFragment()
    }
}