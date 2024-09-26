package com.example.a2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a2.databinding.FragmentCountryDetailsBinding

class CountryDetailsFragment : Fragment() {
    private lateinit var bindingFragmentDerails: FragmentCountryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentDerails = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return bindingFragmentDerails.root
    }

}