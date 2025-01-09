package com.example.a2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2.R
import com.example.a2.data.ArtworkEntity
import com.example.a2.databinding.ItemForOneArtBinding
import com.example.a2.di.RetrofitModule.BASE_URL_FOR_IMAGE
import com.example.a2.di.RetrofitModule.IMAGE_SIZE

class ArtAdapter(private val itemClick: (ArtworkEntity)-> Unit):
    ListAdapter<ArtworkEntity, ArtAdapter.ViewHolder>(ArtDiffCallback) {

    object ArtDiffCallback: DiffUtil.ItemCallback<ArtworkEntity>() {
        override fun areItemsTheSame(
            oldItem: ArtworkEntity,
            newItem: ArtworkEntity
        ): Boolean {
            return oldItem.titleArt == newItem.titleArt
        }

        override fun areContentsTheSame(
            oldItem: ArtworkEntity,
            newItem: ArtworkEntity
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val bindingItem: ItemForOneArtBinding): RecyclerView.ViewHolder(bindingItem.root){
        fun bind(art: ArtworkEntity) = with(bindingItem){
            tvNameArt.text = art.titleArt
            val urlImage = "$BASE_URL_FOR_IMAGE${art.imageArt_id}$IMAGE_SIZE"
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