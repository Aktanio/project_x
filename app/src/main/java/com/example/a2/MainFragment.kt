package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2.databinding.FragmentMainBinding
import com.example.artworks.presentation.ui.ArtListFragment
import com.example.artworks.presentation.ui.ArtSearchFragment
import com.example.countries.presentation.ui.CountriesListFragment
import com.example.countries.presentation.ui.CountriesSearchFragment

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
            openChildFragment(CountriesSearchFragment())
        }
        bindingFragment.listCountry.setOnClickListener {
            openChildFragment(CountriesListFragment())
        }
        bindingFragment.artSearchButton.setOnClickListener {
            openChildFragment(ArtSearchFragment())
        }
        bindingFragment.artListButton.setOnClickListener {
            openChildFragment(ArtListFragment())
        }
    }
    private fun openChildFragment(childFragment: Fragment){
        childFragmentManager.beginTransaction()
            .replace(R.id.childFragmentContainer, childFragment)
            .addToBackStack(null)
            .commit()
    }
}