package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.a2.ArtSearchFragment.Companion.ART_DATA
import com.example.a2.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.a2.RetrofitModule.BASE_URL_FOR_IMAGE
import com.example.a2.RetrofitModule.IMAGE_SIZE
import com.example.a2.databinding.FragmentArtDetailsBinding
import kotlinx.serialization.json.Json

class ArtDetailsFragment : Fragment() {
    private lateinit var bindingArtDetails: FragmentArtDetailsBinding

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
        parentFragmentManager.setFragmentResultListener(ART_KEY_FOR_FRAGMENT, this) { key, bundle ->
            bundle.getString(ART_DATA)?.let {
                val artResponse = Json.decodeFromString<ArtworksResponse.Artwork>(it)
                showInfoArt(artResponse)
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