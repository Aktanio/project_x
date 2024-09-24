package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.a2.databinding.FragmentCountriesSearchBinding

class CountriesSearchFragment : Fragment() {
    private lateinit var bindingFragmentSearch: FragmentCountriesSearchBinding

    companion object{
        const val COUNTRY_NAME = "COUNTRY_NAME"
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
        bindingFragmentSearch.searchButton.setOnClickListener {
            val countryToast = bindingFragmentSearch.countryName.text.toString()
            val toastText = Bundle().apply {
                putString(COUNTRY_NAME, countryToast)
            }
            setFragmentResult(KEY_FOR_FRAGMENT, toastText)

            parentFragmentManager.beginTransaction()
                .replace(R.id.childFragmentContainer, CountriesListFragment())
                .addToBackStack(null)
                .commit()
        }
        bindingFragmentSearch.backToMainFragmentFromSearch.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}