package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a2.databinding.FragmentCountriesSearchBinding

class CountriesSearchFragment : Fragment() {
    private lateinit var bindingFragmentSearch: FragmentCountriesSearchBinding
    private var pauseTime: Long = 0
    private var isPaused: Boolean = false

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
            val action = CountriesSearchFragmentDirections.actionCountriesSearchFragmentToCountriesListFragment(countryToast)
            findNavController().navigate(action)
        }
        bindingFragmentSearch.backToMainFragmentFromSearch.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun onPause() {
        super.onPause()
        pauseTime = System.currentTimeMillis()
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        if (isPaused){
            val resumeTime = System.currentTimeMillis()

            val seconds = (resumeTime - pauseTime) / 1000

            Toast.makeText(context, "Приложение было свернуто на $seconds секунд", Toast.LENGTH_SHORT).show()

            isPaused = false
        }
    }
}