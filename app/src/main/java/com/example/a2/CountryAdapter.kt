package com.example.a2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2.databinding.ItemForOneCountryBinding

class CountryAdapter(private val countries: List<CountryResponse>): RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    class MyViewHolder(val bindingItem: ItemForOneCountryBinding): RecyclerView.ViewHolder(bindingItem.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemForOneCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countries[position]
        holder.bindingItem.theCountry.text = country.name.common
        holder.bindingItem.theCapital.text = country.capital?.joinToString(", ")

        Glide.with(holder.bindingItem.forCountryFlag)
            .load(country.flags.png)
            .into(holder.bindingItem.forCountryFlag)

    }
}