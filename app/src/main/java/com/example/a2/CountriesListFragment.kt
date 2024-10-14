package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2.CountriesSearchFragment.Companion.COUNTRY_DATA
import com.example.a2.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.a2.databinding.FragmentCountriesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class CountriesListFragment : Fragment() {
    private lateinit var bindingFragmentList: FragmentCountriesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentList = FragmentCountriesListBinding.inflate(inflater, container, false)
        return bindingFragmentList.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingFragmentList.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val countries = withContext(Dispatchers.IO){
                    RetrofitClient.countriesAPI.getAllCountry()
                }
                bindingFragmentList.forAllCountry.adapter = CountryAdapter(countries){ selectedCountry->
                    val jsonInfoCountry = Json.encodeToString(CountryResponse.serializer(), selectedCountry)
                    val selectedCountryBundle = Bundle().apply {
                        putString(COUNTRY_DATA,jsonInfoCountry)
                    }
                    childFragmentManager.setFragmentResult(KEY_FOR_FRAGMENT, selectedCountryBundle)

                    childFragmentManager.beginTransaction()
                        .replace(R.id.detailsContainerInList, CountryDetailsFragment())
                        .addToBackStack(null)
                        .commit()
                }
                bindingFragmentList.forAllCountry.layoutManager = LinearLayoutManager(context)

            } catch (e: Exception){
                    Toast.makeText(context, "Ошибка: $e", Toast.LENGTH_SHORT).show()
            }
        }
    }
}