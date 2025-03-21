package com.example.artworks.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artworks.di.ArtworksRetrofitModule.BASE_URL_FOR_IMAGE
import com.example.artworks.di.ArtworksRetrofitModule.IMAGE_SIZE
import com.example.artworks.presentation.dto.ArtworksPresentationDto
import com.example.artworkss.R
import com.example.artworkss.databinding.ItemForOneArtBinding

class ArtAdapter(private val itemClick: (ArtworksPresentationDto)-> Unit):
    ListAdapter<ArtworksPresentationDto, ArtAdapter.ViewHolder>(ArtDiffCallback) {

    object ArtDiffCallback: DiffUtil.ItemCallback<ArtworksPresentationDto>() {
        override fun areItemsTheSame(
            oldItem: ArtworksPresentationDto,
            newItem: ArtworksPresentationDto
        ): Boolean {
            return oldItem.titleArt == newItem.titleArt
        }

        override fun areContentsTheSame(
            oldItem: ArtworksPresentationDto,
            newItem: ArtworksPresentationDto
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val bindingItem: ItemForOneArtBinding): RecyclerView.ViewHolder(bindingItem.root){
        fun bind(art: ArtworksPresentationDto) = with(bindingItem){
            tvNameArt.text = art.titleArt
            val urlImage = "$BASE_URL_FOR_IMAGE${art.imageArtId}$IMAGE_SIZE"
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}