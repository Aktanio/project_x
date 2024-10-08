package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.a2.databinding.FragmentCountriesSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountriesSearchFragment : Fragment() {
    private lateinit var bindingFragmentSearch: FragmentCountriesSearchBinding

    companion object{
        const val CAPITAL_OF_THE_COUNTRY = "CAPITAL_OF_THE_COUNTRY"
        const val FLAG = "FLAG_OF_THE_COUNTRY"
        const val CURRENCIES = "CURRENCIES_OF_THE_COUNTRY"
        const val SUBREGION = "SUBREGION_OF_THE_COUNTRY"
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
            val countryToast = bindingFragmentSearch.countryName.text.toString()
            if (countryToast.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val country = RetrofitClient.countriesAPI.getCountryByName(countryToast)
                        val capital = country[0].capital?.joinToString(", ")
                        val flag = country[0].flags.png
                        val subregion = country[0].subregion
                        val currencies = country[0].currencies.values.firstOrNull()?.name

                        withContext(Dispatchers.Main){
                            val capitalBundle = Bundle().apply {
                                putString(CAPITAL_OF_THE_COUNTRY, capital)
                                putString(FLAG, flag)
                                putString(CURRENCIES, currencies)
                                putString(SUBREGION, subregion)
                            }
                            setFragmentResult(KEY_FOR_FRAGMENT, capitalBundle)

                            parentFragmentManager.beginTransaction()
                                .replace(R.id.childFragmentContainer, CountryDetailsFragment())
                                .addToBackStack(null)
                                .commit()
                        }
                    } catch (e: Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context, "Ошибка: $e", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(context, "Введите название страны", Toast.LENGTH_LONG).show()
            }
        }
    }
}