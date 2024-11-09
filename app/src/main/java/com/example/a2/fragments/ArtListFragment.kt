package com.example.a2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2.adapter.ArtAdapter
import com.example.a2.viewModel.ArtListViewModel
import com.example.a2.data.ArtworksResponse
import com.example.a2.di.MyApp
import com.example.a2.R
import com.example.a2.viewModel.ViewModelFactory
import com.example.a2.fragments.ArtSearchFragment.Companion.ART_DATA
import com.example.a2.fragments.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.a2.databinding.FragmentArtListBinding
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ArtListFragment : Fragment() {
    private lateinit var bindingArtList: FragmentArtListBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val artListViewModel: ArtListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ArtListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MyApp).appComponent.injectArtList(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingArtList = FragmentArtListBinding.inflate(inflater, container, false)
        return bindingArtList.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingArtList.ivBackToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        artListViewModel.artListLiveData.observe(viewLifecycleOwner){artResponse->
            bindingArtList.rvForArtItem.adapter = ArtAdapter(artResponse){ selectedArt->
                val jsonInfoArt = Json.encodeToString(ArtworksResponse.Artwork.serializer(), selectedArt)
                val artBundle = Bundle().apply {
                    putString(ART_DATA, jsonInfoArt)
                }
                childFragmentManager.setFragmentResult(ART_KEY_FOR_FRAGMENT, artBundle)

                childFragmentManager.beginTransaction()
                    .replace(R.id.artDetailsContainerInList, ArtDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        bindingArtList.rvForArtItem.layoutManager = LinearLayoutManager(context)
        artListViewModel.requestAllArtworks()
    }
}