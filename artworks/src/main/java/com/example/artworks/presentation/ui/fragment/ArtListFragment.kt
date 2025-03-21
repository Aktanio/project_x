package com.example.artworks.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artworks.presentation.error.AppError
import com.example.artworks.presentation.dto.ArtworksPresentationDto
import com.example.artworks.presentation.ui.adapter.ArtAdapter
import com.example.artworks.presentation.ui.fragment.ArtSearchFragment.Companion.ART_DATA
import com.example.artworks.presentation.ui.fragment.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.artworks.presentation.viewmodel.ArtListViewModel
import com.example.artworkss.R
import com.example.artworkss.databinding.FragmentArtListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class ArtListFragment : Fragment() {
    private lateinit var bindingArtList: FragmentArtListBinding
    private val artListViewModel: ArtListViewModel by viewModels()
    private val artAdapter by lazy {
        ArtAdapter{selectedArt->
            val jsonInfoArt = Json.encodeToString(ArtworksPresentationDto.serializer(), selectedArt)
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

        bindingArtList.rvForArtItem.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItem) >= totalItemCount && firstVisibleItem >= 0){
                    artListViewModel.onPageFinished()
                }
            }
        })
        artListViewModel.errorLiveData.observe(viewLifecycleOwner){ error->
            when(error){
                is AppError.NoDataError -> Toast.makeText(context, R.string.errorMessageInList, Toast.LENGTH_SHORT).show()
                is AppError.PartialDataError -> Toast.makeText(context, R.string.partialDataError, Toast.LENGTH_SHORT).show()
            }
        }

        bindingArtList.rvForArtItem.adapter = artAdapter
        artListViewModel.artListLiveData.observe(viewLifecycleOwner){artworks->
            artAdapter.submitList(artworks)
        }
        bindingArtList.rvForArtItem.layoutManager = LinearLayoutManager(context)
    }
}