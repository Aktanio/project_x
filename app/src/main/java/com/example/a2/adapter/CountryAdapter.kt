package com.example.a2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2.data.CountryResponse
import com.example.a2.databinding.ItemForOneCountryBinding

class CountryAdapter(private val itemClick: (CountryResponse) -> Unit):
    ListAdapter<CountryResponse, CountryAdapter.MyViewHolder>(CountryDiffCallback) {

    object CountryDiffCallback: DiffUtil.ItemCallback<CountryResponse>() {
        override fun areItemsTheSame(
            oldItem: CountryResponse,
            newItem: CountryResponse
        ): Boolean {
            return oldItem.name.common == newItem.name.common
        }

        override fun areContentsTheSame(
            oldItem: CountryResponse,
            newItem: CountryResponse
        ): Boolean {
            return oldItem == newItem
        }

    }


    inner class MyViewHolder(private val bindingItem: ItemForOneCountryBinding): RecyclerView.ViewHolder(bindingItem.root){
        fun bind(country: CountryResponse){
            bindingItem.theCountry.text = country.name.common
            bindingItem.theCapital.text = country.capital?.joinToString(", ")
            Glide.with(bindingItem.forCountryFlag)
                .load(country.flags.png)
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