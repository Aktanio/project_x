package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2.ArtSearchFragment.Companion.ART_DATA
import com.example.a2.ArtSearchFragment.Companion.ART_KEY_FOR_FRAGMENT
import com.example.a2.databinding.FragmentArtListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ArtListFragment : Fragment() {
    private lateinit var bindingArtList: FragmentArtListBinding
    @Inject
    lateinit var artworksAPI: ArtworksAPI

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
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val artworks = withContext(Dispatchers.IO){
                    artworksAPI.getAllArtworks().data
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