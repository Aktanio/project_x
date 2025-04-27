package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2.databinding.FragmentMainBinding
import com.example.artworks_api.contract.ArtworksNavigator
import com.example.countries_api.contract.CountriesNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject lateinit var artworksNavigator: ArtworksNavigator
    @Inject lateinit var countriesNavigator: CountriesNavigator

    private lateinit var bindingFragment: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragment = FragmentMainBinding.inflate(inflater, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindingFragment.searchCountry.setOnClickListener {
            openChildFragment(countriesNavigator.openCountrySearchFragment())
        }
        bindingFragment.listCountry.setOnClickListener {
            openChildFragment(countriesNavigator.openCountryListFragment())
        }
        bindingFragment.artSearchButton.setOnClickListener {
            openChildFragment(artworksNavigator.openArtSearchFragment())
        }
        bindingFragment.artListButton.setOnClickListener {
            openChildFragment(artworksNavigator.openArtListFragment())
        }
    }
    private fun openChildFragment(childFragment: Fragment){
        childFragmentManager.beginTransaction()
            .replace(R.id.childFragmentContainer, childFragment)
            .addToBackStack(null)
            .commit()
    }
}