package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.a2.CountriesSearchFragment.Companion.COUNTRY_DATA
import com.example.a2.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.a2.databinding.FragmentCountryDetailsBinding
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

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
            bundle.getString(COUNTRY_DATA)?.let {json->
                val countryResponse = Json.decodeFromString(ListSerializer(CountryResponse.serializer()), json)
                countryDetailsViewModel.infoCountry(countryResponse)
            }
        }
    }
    private fun updateUI(country: CountryResponse) = with(bindingFragmentDetails){
        subregion.text = country.subregion
        currencies.text = country.currencies?.values?.firstOrNull()?.name
        countryName.text = country.name.common
        capital.text = country.capital?.joinToString(", ")
        regionOfTheCountry.text = country.region
        populationOfTheCountry.text = country.population.toString()
        continentsOfTheCountry.text = country.continents[0]
        languagesOfTheCountry.text = country.languages?.values?.firstOrNull()
        signsCars.text = country.car.signs[0]
        sideCars.text = country.car.side
        officialName.text = country.name.nativeName?.values?.firstOrNull()?.official
        officialNameEng.text = country.name.official
        areaOfTheCountry.text = country.area.toString()
        Glide.with(this@CountryDetailsFragment)
            .load(country.flags.png)
            .into(countryFlag)
    }
}