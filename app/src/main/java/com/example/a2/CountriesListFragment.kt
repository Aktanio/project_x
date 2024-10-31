package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2.CountriesSearchFragment.Companion.COUNTRY_DATA
import com.example.a2.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.a2.databinding.FragmentCountriesListBinding
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CountriesListFragment : Fragment() {
    private lateinit var bindingFragmentList: FragmentCountriesListBinding
    @Inject
    lateinit var viewModelFactory: CountryViewModelFactory
    private lateinit var listCountryViewModel: CountryListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApp).appComponent.injectCountryList(this)
        listCountryViewModel = ViewModelProvider(this, viewModelFactory).get(CountryListViewModel::class.java)
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
        listCountryViewModel.getAllCountries()
        listCountryViewModel.countriesLiveData.observe(viewLifecycleOwner){countryResponse->
            bindingFragmentList.forAllCountry.adapter = CountryAdapter(countryResponse){selectedMeal->
                val jsonInfoCountry = Json.encodeToString(ListSerializer(CountryResponse.serializer()),listOf(selectedMeal))
                val selectedCountryBundle = Bundle().apply {
                    putString(COUNTRY_DATA, jsonInfoCountry)
                }
                childFragmentManager.setFragmentResult(KEY_FOR_FRAGMENT, selectedCountryBundle)

                childFragmentManager.beginTransaction()
                    .replace(R.id.detailsContainerInList, CountryDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
            bindingFragmentList.forAllCountry.layoutManager = LinearLayoutManager(context)
        }
    }
}