package com.example.a2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.example.a2.CountriesSearchFragment.Companion.CAPITAL_OF_THE_COUNTRY
import com.example.a2.CountriesSearchFragment.Companion.CURRENCIES
import com.example.a2.CountriesSearchFragment.Companion.FLAG
import com.example.a2.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.a2.CountriesSearchFragment.Companion.SUBREGION
import com.example.a2.databinding.FragmentCountryDetailsBinding

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
            val toastText = bundle.getString(CAPITAL_OF_THE_COUNTRY)
            if (!toastText.isNullOrEmpty()){
                Toast.makeText(context, "Столица: $toastText", Toast.LENGTH_LONG).show()
            }
            val flag = bundle.getString(FLAG)
            Glide.with(this)
                .load(flag)
                .into(bindingFragmentDetails.countryFlag)

            val currencies = bundle.getString(CURRENCIES)
            bindingFragmentDetails.currencies.text = currencies

            val subregion = bundle.getString(SUBREGION)
            bindingFragmentDetails.subregion.text = subregion
        }
        bindingFragmentDetails.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}