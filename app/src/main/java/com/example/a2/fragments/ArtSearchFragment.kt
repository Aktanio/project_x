package com.example.a2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a2.viewModel.ArtSearchViewModel
import com.example.a2.di.MyApp
import com.example.a2.R
import com.example.a2.viewModel.ViewModelFactory
import com.example.a2.databinding.FragmentArtSearchBinding
import javax.inject.Inject

class ArtSearchFragment : Fragment() {
    private lateinit var bindingArtSearch: FragmentArtSearchBinding
    @Inject
    lateinit var artSearchViewModelFactory: ViewModelFactory
    private val artSearchViewModel: ArtSearchViewModel by lazy {
        ViewModelProvider(requireActivity(), artSearchViewModelFactory).get(ArtSearchViewModel::class.java)
    }

    companion object {
        const val ART_DATA = "ART_DATA"
        const val ART_KEY_FOR_FRAGMENT = "ART_KEY_FOR_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApp).appComponent.injectArtSearch(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingArtSearch = FragmentArtSearchBinding.inflate(inflater, container, false)
        return bindingArtSearch.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        artSearchViewModel.artInfoLiveData.observe(viewLifecycleOwner){jsonArtInfo->
            val artBundle = Bundle().apply {
                putString(ART_DATA, jsonArtInfo)
            }
            childFragmentManager.setFragmentResult(ART_KEY_FOR_FRAGMENT, artBundle)
        }
        bindingArtSearch.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        bindingArtSearch.searchArtButton.setOnClickListener {

            if (bindingArtSearch.nameArtSearch.text.isNotEmpty()) {
                artSearchViewModel.onSearchButtonClicked(bindingArtSearch.nameArtSearch.text.toString())

                childFragmentManager.beginTransaction()
                    .replace(R.id.artDetailsContainerInSearch, ArtDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            } else{
                Toast.makeText(context, "Введите название страны", Toast.LENGTH_SHORT).show()
            }
        }
    }
}