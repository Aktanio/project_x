package com.example.a2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2.R
import com.example.a2.adapter.CountryAdapter
import com.example.a2.data.CountryEntity
import com.example.a2.databinding.FragmentCountriesListBinding
import com.example.a2.fragments.CountriesSearchFragment.Companion.COUNTRY_DATA
import com.example.a2.fragments.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.a2.viewModel.CountryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class CountriesListFragment : Fragment() {
    private lateinit var bindingFragmentList: FragmentCountriesListBinding
    private val listCountryViewModel: CountryListViewModel by viewModels()
    private val countryAdapter by lazy {
        CountryAdapter{selectedMeal->
            val jsonInfoCountry = Json.encodeToString(CountryEntity.serializer(),selectedMeal)
            val selectedCountryBundle = Bundle().apply {
                putString(COUNTRY_DATA, jsonInfoCountry)
            }
            childFragmentManager.setFragmentResult(KEY_FOR_FRAGMENT, selectedCountryBundle)

            childFragmentManager.beginTransaction()
                .replace(R.id.detailsContainerInList, CountryDetailsFragment())
                .addToBackStack(null)
                .commit()
        }
    }

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
        bindingFragmentList.forAllCountry.adapter = countryAdapter
        listCountryViewModel.countriesLiveData.observe(viewLifecycleOwner){countryResponse->
            countryAdapter.submitList(countryResponse)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                listCountryViewModel.errorSharedFlow.collect{
                    Toast.makeText(context, R.string.errorMessageInList, Toast.LENGTH_SHORT).show()
                }
            }
        }
        bindingFragmentList.forAllCountry.layoutManager = LinearLayoutManager(context)
        listCountryViewModel.requestAllCountries()
    }
}