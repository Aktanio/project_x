package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        val args = CountriesListFragmentArgs.fromBundle(requireArguments())
        val toastText = args.toastData
        if (toastText.isNotEmpty()){
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
        }
        bindingFragmentList.backToMainFragment.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}