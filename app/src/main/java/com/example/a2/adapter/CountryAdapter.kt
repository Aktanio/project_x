package com.example.a2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2.data.CountryEntity
import com.example.a2.databinding.ItemForOneCountryBinding

class CountryAdapter(private val itemClick: (CountryEntity) -> Unit):
    ListAdapter<CountryEntity, CountryAdapter.MyViewHolder>(CountryDiffCallback) {

    object CountryDiffCallback: DiffUtil.ItemCallback<CountryEntity>() {
        override fun areItemsTheSame(
            oldItem: CountryEntity,
            newItem: CountryEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CountryEntity,
            newItem: CountryEntity
        ): Boolean {
            return oldItem == newItem
        }

    }


    inner class MyViewHolder(private val bindingItem: ItemForOneCountryBinding): RecyclerView.ViewHolder(bindingItem.root){
        fun bind(country: CountryEntity){
            bindingItem.theCountry.text = country.commonName
            bindingItem.theCapital.text = country.capital
            Glide.with(bindingItem.forCountryFlag)
                .load(country.flagsPng)
                .into(bindingItem.forCountryFlag)
            bindingItem.root.setOnClickListener{
                itemClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemForOneCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}