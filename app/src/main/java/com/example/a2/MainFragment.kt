package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a2.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var bindingFragment: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragment = FragmentMainBinding.inflate(inflater, container, false)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val controller = findNavController()
        bindingFragment.searchCountry.setOnClickListener {controller.navigate(R.id.countriesSearchFragment)}
        bindingFragment.listCountry.setOnClickListener {controller.navigate(R.id.countriesListFragment)}
    }
}