package com.example.a2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2.data.CountryResponse
import com.example.a2.databinding.ItemForOneCountryBinding

class CountryAdapter(private val itemClick: (CountryResponse) -> Unit): RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    private var countries: List<CountryResponse> = emptyList()

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

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    fun updateData(newCountries: List<CountryResponse>){
        val diffCallback = GenericDiffUtil(
            oldList = countries,
            newList = newCountries,
            areItemsTheSame = {oldItem, newItem -> oldItem.name.common == newItem.name.common},
            areContentsTheSame = {oldItem, newItem -> oldItem == newItem}
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        countries = newCountries
        diffResult.dispatchUpdatesTo(this)
    }
}