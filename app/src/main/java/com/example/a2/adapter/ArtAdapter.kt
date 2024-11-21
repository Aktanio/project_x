package com.example.a2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2.data.ArtworksResponse
import com.example.a2.R
import com.example.a2.di.RetrofitModule.BASE_URL_FOR_IMAGE
import com.example.a2.di.RetrofitModule.IMAGE_SIZE
import com.example.a2.databinding.ItemForOneArtBinding

class ArtAdapter(private val artworks: List<ArtworksResponse.Artwork>,
                 private val itemClick: (ArtworksResponse.Artwork)-> Unit): RecyclerView.Adapter<ArtAdapter.ViewHolder>() {


    inner class ViewHolder(private val bindingItem: ItemForOneArtBinding): RecyclerView.ViewHolder(bindingItem.root){
        fun bind(art: ArtworksResponse.Artwork) = with(bindingItem){
            tvNameArt.text = art.title
            val urlImage = "$BASE_URL_FOR_IMAGE${art.image_id}$IMAGE_SIZE"
            Glide.with(ivOneArt)
                .load(urlImage)
                .placeholder(R.drawable.default_image)
                .into(ivOneArt)
            root.setOnClickListener {
                itemClick(art)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemForOneArtBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return artworks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artworks[position])
    }
}