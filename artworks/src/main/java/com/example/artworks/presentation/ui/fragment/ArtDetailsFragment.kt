package com.example.artworks.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.artworks.di.ArtworksRetrofitModule.BASE_URL_FOR_IMAGE
import com.example.artworks.di.ArtworksRetrofitModule.IMAGE_SIZE
import com.example.artworks.presentation.dto.ArtworksPresentationDto
import com.example.artworks.presentation.ui.fragment.ArtSearchFragment.Companion.ART_DATA
import com.example.artworks.presentation.ui.fragment.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.artworks.presentation.viewmodel.ArtDetailsViewModel
import com.example.artworkss.R
import com.example.artworkss.databinding.FragmentArtDetailsBinding

class ArtDetailsFragment : Fragment() {
    private lateinit var bindingArtDetails: FragmentArtDetailsBinding
    private val artDetailsViewModel: ArtDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingArtDetails = FragmentArtDetailsBinding.inflate(inflater, container, false)
        return bindingArtDetails.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingArtDetails.ivBackToParentFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        artDetailsViewModel.artDetailLiveData.observe(viewLifecycleOwner){art->
            showInfoArt(art)
        }
        parentFragmentManager.setFragmentResultListener(ART_KEY_FOR_FRAGMENT, this) { key, bundle ->
            bundle.getString(ART_DATA)?.let { jsonInfoArt->
                artDetailsViewModel.onArtReceived(jsonInfoArt)
            }
        }
    }

    private fun showInfoArt(art: ArtworksPresentationDto) = with(bindingArtDetails) {
        tvArtName.text = art.titleArt
        tvAboutArtist.text = art.artistArtDisplay
        tvStyleArt.text = art.styleArtTitle
        tvYearTheCreation.text = art.dateArtDisplay
        Glide.with(this@ArtDetailsFragment)
            .load("$BASE_URL_FOR_IMAGE${art.imageArtId}$IMAGE_SIZE")
            .placeholder(R.drawable.default_image)
            .into(ivArt)
    }
}