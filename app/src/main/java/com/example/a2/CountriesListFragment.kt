package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.a2.CountriesSearchFragment.Companion.COUNTRY_NAME
import com.example.a2.CountriesSearchFragment.Companion.KEY_FOR_FRAGMENT
import com.example.a2.databinding.FragmentCountriesListBinding

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
        setFragmentResultListener(KEY_FOR_FRAGMENT){key, bundle->
            val toastText = bundle.getString(COUNTRY_NAME)
            if (!toastText.isNullOrEmpty()){
                Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
            }
        }
        bindingFragmentList.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}