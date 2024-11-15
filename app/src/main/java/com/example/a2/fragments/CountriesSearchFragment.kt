package com.example.a2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.a2.R
import com.example.a2.databinding.FragmentCountriesSearchBinding
import com.example.a2.di.MyApp
import com.example.a2.viewModel.CountrySearchViewModel
import com.example.a2.viewModel.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesSearchFragment : Fragment() {
    private lateinit var bindingFragmentSearch: FragmentCountriesSearchBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val countrySearchViewModel: CountrySearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CountrySearchViewModel::class.java)
    }

    companion object {
        const val COUNTRY_DATA = "COUNTRY_DATA"
        const val KEY_FOR_FRAGMENT = "KEY_FOR_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApp).appComponent.injectCountrySearch(this)
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