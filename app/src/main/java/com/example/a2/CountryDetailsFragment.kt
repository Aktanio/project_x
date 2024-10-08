package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.example.a2.CountriesSearchFragment.Companion.COUNTRY_DATA
import com.example.a2.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.a2.databinding.FragmentCountryDetailsBinding
import kotlinx.serialization.json.Json

class CountryDetailsFragment : Fragment() {
    private lateinit var bindingFragmentDetails: FragmentCountryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentDetails = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return bindingFragmentDetails.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFragmentResultListener(KEY_FOR_FRAGMENT){ key, bundle->
            val jsonBundle = bundle.getString(COUNTRY_DATA)
            jsonBundle?.let {
                val countryResponse = Json.decodeFromString(CountryResponse.serializer(), jsonBundle)
                bindingFragmentDetails.subregion.text = countryResponse.subregion
                bindingFragmentDetails.currencies.text = countryResponse.currencies.values.firstOrNull()?.name
                Glide.with(this)
                    .load(countryResponse.flags.png)
                    .into(bindingFragmentDetails.countryFlag)
            }
        }
        bindingFragmentDetails.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}