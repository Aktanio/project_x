package com.example.a2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a2.databinding.FragmentArtSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ArtSearchFragment : Fragment() {
    private lateinit var bindingArtSearch: FragmentArtSearchBinding
    @Inject
    lateinit var artworksAPI: ArtworksAPI

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
        bindingArtSearch.backToMainFragment.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        bindingArtSearch.searchArtButton.setOnClickListener {
            val nameArt = bindingArtSearch.nameArtSearch.text.toString()
            if (nameArt.isNotEmpty()) {
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        val artResponse = withContext(Dispatchers.IO) {
                            artworksAPI.getArtByName(nameArt).data[0]
                        }
                        val json = Json.encodeToString(ArtworksResponse.Artwork.serializer(), artResponse)

                        val artBundle = Bundle().apply {
                            putString(ART_DATA, json)
                        }
                        childFragmentManager.setFragmentResult(ART_KEY_FOR_FRAGMENT, artBundle)

                        childFragmentManager.beginTransaction()
                            .replace(R.id.artDetailsContainerInSearch, ArtDetailsFragment())
                            .addToBackStack(null)
                            .commit()

                    } catch (e: Exception) {
                        Toast.makeText(context, "Ошибка $e", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Введите название", Toast.LENGTH_SHORT).show()
            }
        }
    }
}