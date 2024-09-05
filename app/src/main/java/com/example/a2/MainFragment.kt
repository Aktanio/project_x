package com.example.a2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val intentSearchActivity = Intent(activity, CountriesSearchActivity::class.java)
            startActivity(intentSearchActivity)
        }
        bindingFragment.listCountry.setOnClickListener {
            val intentListActivity = Intent(activity, CountriesListActivity::class.java)
            startActivity(intentListActivity)
        }
    }
}