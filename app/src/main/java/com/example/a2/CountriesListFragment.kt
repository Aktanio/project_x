package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2.databinding.FragmentCountriesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val countries = RetrofitClient.countriesAPI.getAllCountry()

                withContext(Dispatchers.Main){
                    bindingFragmentList.forAllCountry.adapter = CountryAdapter(countries)
                    bindingFragmentList.forAllCountry.layoutManager = LinearLayoutManager(context)
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, "Ошибка: $e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}