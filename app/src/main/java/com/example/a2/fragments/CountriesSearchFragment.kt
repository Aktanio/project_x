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
import com.example.a2.R
import com.example.a2.databinding.FragmentCountriesSearchBinding
import com.example.a2.viewModel.CountrySearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountriesSearchFragment : Fragment() {
    private lateinit var bindingFragmentSearch: FragmentCountriesSearchBinding
    private val countrySearchViewModel: CountrySearchViewModel by viewModels()

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                countrySearchViewModel.countryInfoSharedFlow.collect{jsonInfoCountry->
                    val countryBundle = Bundle().apply {
                        putString(COUNTRY_DATA, jsonInfoCountry)
                    }
                    childFragmentManager.setFragmentResult(KEY_FOR_FRAGMENT, countryBundle)

                    childFragmentManager.beginTransaction()
                        .replace(R.id.detailsContainerInSearch, CountryDetailsFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                countrySearchViewModel.errorSharedFlow.collect{ error->
                    Toast.makeText(context, R.string.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        bindingFragmentSearch.backToMainFragmentFromSearch.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        bindingFragmentSearch.searchButton.setOnClickListener {
            if (bindingFragmentSearch.countryName.text.isNotEmpty()) {

                countrySearchViewModel.onSearchButtonClicked(bindingFragmentSearch.countryName.text.toString())

            } else{
                Toast.makeText(context, "Введите название страны", Toast.LENGTH_SHORT).show()
            }
        }
    }
}