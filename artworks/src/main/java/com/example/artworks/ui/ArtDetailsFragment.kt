package com.example.artworks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.artworks.data.ArtworkEntity
import com.example.artworks.di.ArtworksRetrofitModule.BASE_URL_FOR_IMAGE
import com.example.artworks.di.ArtworksRetrofitModule.IMAGE_SIZE
import com.example.artworks.ui.ArtSearchFragment.Companion.ART_DATA
import com.example.artworks.ui.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.artworks.viewmodel.ArtDetailsViewModel
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

    private fun showInfoArt(art: ArtworkEntity) = with(bindingArtDetails) {
        tvArtName.text = art.titleArt
        tvAboutArtist.text = art.artist_artDisplay
        tvStyleArt.text = art.style_artTitle
        tvYearTheCreation.text = art.date_artDisplay
        Glide.with(this@ArtDetailsFragment)
            .load("$BASE_URL_FOR_IMAGE${art.imageArt_id}$IMAGE_SIZE")
            .placeholder(R.drawable.default_image)
            .into(ivArt)
    }
}