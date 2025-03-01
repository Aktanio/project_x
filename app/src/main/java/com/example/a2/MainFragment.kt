package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        bindingFragment.searchCountry.setOnClickListener {
            openChildFragment(com.example.countries.ui.CountriesSearchFragment())
        }
        bindingFragment.listCountry.setOnClickListener {
            openChildFragment(com.example.countries.ui.CountriesListFragment())
        }
        bindingFragment.artSearchButton.setOnClickListener {
            openChildFragment(com.example.artworks.ui.ArtSearchFragment())
        }
        bindingFragment.artListButton.setOnClickListener {
            openChildFragment(com.example.artworks.ui.ArtListFragment())
        }
    }
    private fun openChildFragment(childFragment: Fragment){
        childFragmentManager.beginTransaction()
            .replace(R.id.childFragmentContainer, childFragment)
            .addToBackStack(null)
            .commit()
    }
}