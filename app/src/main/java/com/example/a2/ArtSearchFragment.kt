package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a2.databinding.FragmentArtSearchBinding
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ArtSearchFragment : Fragment() {
    private lateinit var bindingArtSearch: FragmentArtSearchBinding
    @Inject
    lateinit var artSearchViewModelFactory: ArtViewModelFactory
    private lateinit var artSearchViewModel: ArtSearchViewModel

    companion object {
        const val ART_DATA = "ART_DATA"
        const val ART_KEY_FOR_FRAGMENT = "ART_KEY_FOR_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApp).appComponent.injectArtSearch(this)
        artSearchViewModel = ViewModelProvider(requireActivity(), artSearchViewModelFactory).get(ArtSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingArtSearch = FragmentArtSearchBinding.inflate(inflater, container, false)
        return bindingArtSearch.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingArtSearch.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        bindingArtSearch.searchArtButton.setOnClickListener {
            val nameArt = bindingArtSearch.nameArtSearch.text.toString()
            if (nameArt.isNotEmpty()) {
                artSearchViewModel.getArtByName(nameArt)
                artSearchViewModel.artSearchLiveData.observe(viewLifecycleOwner){artResponse->
                    val jsonArtInfo = Json.encodeToString(ArtworksResponse.Artwork.serializer(), artResponse)
                    val artBundle = Bundle().apply {
                        putString(ART_DATA, jsonArtInfo)
                    }
                    childFragmentManager.setFragmentResult(ART_KEY_FOR_FRAGMENT, artBundle)
                }
                    childFragmentManager.beginTransaction()
                        .replace(R.id.artDetailsContainerInSearch, ArtDetailsFragment())
                        .addToBackStack(null)
                        .commit()
            } else {
                Toast.makeText(context, "Введите название", Toast.LENGTH_SHORT).show()
            }
        }
    }
}