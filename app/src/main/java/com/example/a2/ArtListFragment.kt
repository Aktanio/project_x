package com.example.a2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.a2.ArtSearchFragment.Companion.ART_DATA
import com.example.a2.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.a2.RetrofitArtClient.BASE_URL_FOR_IMAGE
import com.example.a2.RetrofitArtClient.IMAGE_SIZE
import com.example.a2.databinding.FragmentArtListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ArtListFragment : Fragment() {
    private lateinit var bindingArtList: FragmentArtListBinding

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
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val artworks = withContext(Dispatchers.IO){
                    RetrofitArtClient.artAPI.getAllArtworks().data
                }
                    bindingArtList.rvForArtItem.adapter = ArtAdapter(artworks){selectedArt->
                        val jsonInfoArt = Json.encodeToString(ArtworksResponse.Artwork.serializer(), selectedArt)
                        val selectedArtBundle = Bundle().apply {
                            putString(ART_DATA, jsonInfoArt)
                        }
                        childFragmentManager.setFragmentResult(ART_KEY_FOR_FRAGMENT, selectedArtBundle)

                        childFragmentManager.beginTransaction()
                            .replace(R.id.artDetailsContainerInList, ArtDetailsFragment())
                            .addToBackStack(null)
                            .commit()
                    }

                bindingArtList.rvForArtItem.layoutManager = LinearLayoutManager(context)
            }catch (e:Exception){
                Toast.makeText(context, "Ошибка $e", Toast.LENGTH_LONG).show()
            }
        }
    }
}