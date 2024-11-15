package com.example.a2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.a2.R
import com.example.a2.data.ArtworksResponse
import com.example.a2.databinding.FragmentArtDetailsBinding
import com.example.a2.di.RetrofitModule.BASE_URL_FOR_IMAGE
import com.example.a2.di.RetrofitModule.IMAGE_SIZE
import com.example.a2.fragments.ArtSearchFragment.Companion.ART_DATA
import com.example.a2.fragments.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.a2.viewModel.ArtDetailsViewModel

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
                artDetailsViewModel.requestArt(jsonInfoArt)
            }
        }
    }

    private fun showInfoArt(art: ArtworksResponse.Artwork) = with(bindingArtDetails) {
        tvArtName.text = art.title
        tvAboutArtist.text = art.artist_display
        tvStyleArt.text = art.style_title
        tvYearTheCreation.text = art.date_display
        Glide.with(this@ArtDetailsFragment)
            .load("$BASE_URL_FOR_IMAGE${art.image_id}$IMAGE_SIZE")
            .placeholder(R.drawable.default_image)
            .into(ivArt)
    }
}