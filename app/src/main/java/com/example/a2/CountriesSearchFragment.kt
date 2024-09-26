package com.example.a2

import android.os.Bundle
import android.util.Log
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
            if (countryToast.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val responce = RetrofitClient.countriesAPI.getCountryByName(countryToast)
                        val capital = responce[0].capital?.joinToString(", ")

                        withContext(Dispatchers.Main){
                            val capitalToast = Bundle().apply {
                                putString(COUNTRY_NAME, capital)
                            }
                            setFragmentResult(KEY_FOR_FRAGMENT, capitalToast)

                            parentFragmentManager.beginTransaction()
                                .replace(R.id.childFragmentContainer, CountriesListFragment())
                                .addToBackStack(null)
                                .commit()

                            bindingFragmentSearch.backToMainFragmentFromSearch.setOnClickListener {
                                parentFragmentManager.popBackStack()
                            }
                        }
                    } catch (e: Exception){
                        Toast.makeText(context, "Ошибка: $e", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(context, "Введите название страны", Toast.LENGTH_LONG).show()
            }
        }
    }
}