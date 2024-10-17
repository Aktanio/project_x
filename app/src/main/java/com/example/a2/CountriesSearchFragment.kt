package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a2.databinding.FragmentCountriesSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class CountriesSearchFragment : Fragment() {
    private lateinit var bindingFragmentSearch: FragmentCountriesSearchBinding

    companion object {
        const val COUNTRY_DATA = "COUNTRY_DATA"
        const val KEY_FOR_FRAGMENT = "KEY_FOR_FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentSearch = FragmentCountriesSearchBinding.inflate(inflater, container, false)
        return bindingFragmentSearch.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingFragmentSearch.backToMainFragmentFromSearch.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        bindingFragmentSearch.searchButton.setOnClickListener {
            val countryName = bindingFragmentSearch.countryName.text.toString()
            if (countryName.isNotEmpty()) {
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        val country = withContext(Dispatchers.IO) {
                            RetrofitCountriesClient.countriesAPI.getCountryByName(countryName)
                        }
                        val firstCountry = country.firstOrNull()

                        firstCountry?.let {
                            val jsonAboutCountry =
                                Json.encodeToString(CountryResponse.serializer(), it)

                            val countryBundle = Bundle().apply {
                                putString(COUNTRY_DATA, jsonAboutCountry)
                            }

                            childFragmentManager.setFragmentResult(KEY_FOR_FRAGMENT, countryBundle)

                            childFragmentManager.beginTransaction()
                                .replace(R.id.detailsContainerInSearch, CountryDetailsFragment())
                                .addToBackStack(null)
                                .commit()
                        }

                    } catch (e: Exception) {
                        Toast.makeText(context, "Ошибка: $e", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Введите название страны", Toast.LENGTH_LONG).show()
            }
        }
    }
}