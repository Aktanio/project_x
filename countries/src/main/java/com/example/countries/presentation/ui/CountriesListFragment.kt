package com.example.countries.presentation.ui

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
import com.example.countries.domain.entity.CountryEntity
import com.example.countries.AppError
import com.example.countries.R
import com.example.countries.presentation.adapter.CountryAdapter
import com.example.countries.databinding.FragmentCountriesListBinding
import com.example.countries.presentation.ui.CountriesSearchFragment.Companion.COUNTRY_DATA
import com.example.countries.presentation.ui.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.countries.presentation.viewmodel.CountryListViewModel
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
                listCountryViewModel.errorSharedFlow.collect{ error->
                    when(error){
                        is AppError.NoDataError -> Toast.makeText(context, R.string.errorMessageInList, Toast.LENGTH_SHORT).show()
                        is AppError.PartialDataError -> Toast.makeText(context, R.string.partialDataError, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        bindingFragmentList.forAllCountry.layoutManager = LinearLayoutManager(context)
        listCountryViewModel.requestAllCountries()
    }
}