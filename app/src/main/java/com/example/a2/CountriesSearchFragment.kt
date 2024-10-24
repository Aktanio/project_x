package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a2.databinding.FragmentCountriesSearchBinding
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CountriesSearchFragment : Fragment() {
    private lateinit var bindingFragmentSearch: FragmentCountriesSearchBinding
    @Inject
    lateinit var viewModelFactory: CountryViewModelFactory
    private lateinit var countrySearchViewModel: CountrySearchViewModel

    companion object {
        const val COUNTRY_DATA = "COUNTRY_DATA"
        const val KEY_FOR_FRAGMENT = "KEY_FOR_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApp).appComponent.injectCountrySearch(this)
        countrySearchViewModel = ViewModelProvider(this, viewModelFactory).get(CountrySearchViewModel::class.java)
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
                countrySearchViewModel.getCountryByName(countryName)

                countrySearchViewModel.countrySearchLiveData.observe(viewLifecycleOwner){ countryResponse->
                    val jsonInfoCountry = Json.encodeToString(ListSerializer(CountryResponse.serializer()), countryResponse)
                    val countryBundle = Bundle().apply {
                        putString(COUNTRY_DATA, jsonInfoCountry)
                    }
                    childFragmentManager.setFragmentResult(KEY_FOR_FRAGMENT, countryBundle)

                    childFragmentManager.beginTransaction()
                        .replace(R.id.detailsContainerInSearch, CountryDetailsFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }else{
                Toast.makeText(context, "Введите название страны", Toast.LENGTH_SHORT).show()
            }
        }
    }
}