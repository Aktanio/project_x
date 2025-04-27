package com.example.countries.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.countries.databinding.FragmentCountryDetailsBinding
import com.example.countries.presentation.dto.CountryPresentationDto
import com.example.countries.presentation.ui.fragment.CountriesSearchFragment.Companion.COUNTRY_DATA
import com.example.countries.presentation.ui.fragment.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.countries.presentation.viewmodel.CountryDetailsViewModel

class CountryDetailsFragment : Fragment() {
    private lateinit var bindingFragmentDetails: FragmentCountryDetailsBinding
    private val countryDetailsViewModel: CountryDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentDetails = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return bindingFragmentDetails.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindingFragmentDetails.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        countryDetailsViewModel.countryDetailsLiveData.observe(viewLifecycleOwner){country->
            updateUI(country)
        }
        parentFragmentManager.setFragmentResultListener(KEY_FOR_FRAGMENT, this){ key, bundle->
            bundle.getString(COUNTRY_DATA)?.let {jsonInfoCountry->
                countryDetailsViewModel.onCountryReceived(jsonInfoCountry)
            }
        }
    }
    private fun updateUI(country: CountryPresentationDto) = with(bindingFragmentDetails){
        subregion.text = country.subregion
        currencies.text = country.currencies
        countryName.text = country.commonName
        capital.text = country.capital
        regionOfTheCountry.text = country.region
        populationOfTheCountry.text = country.population.toString()
        continentsOfTheCountry.text = country.continents
        languagesOfTheCountry.text = country.languages
        signsCars.text = country.carSigns
        sideCars.text = country.carSide
        officialName.text = country.nativeName
        officialNameEng.text = country.officialName
        areaOfTheCountry.text = country.area.toString()
        Glide.with(this@CountryDetailsFragment)
            .load(country.flagsPng)
            .into(countryFlag)
    }
}