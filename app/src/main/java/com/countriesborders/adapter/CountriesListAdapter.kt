package com.countriesborders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.countriesborders.R
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.App


class CountriesListAdapter(private val callback: CountryViewHolder.OnCountryClickListener?)
    : PagedListAdapter<CountryEntity, CountryViewHolder>(CountriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(App.context!!).inflate(R.layout.country_viewholder, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val model = getItem(position)
        model?.let {
            holder.bind(model, callback)
        }
    }

    class CountriesDiffCallback : DiffUtil.ItemCallback<CountryEntity>() {
        override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem.countryName == newItem.countryName
        }

        override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem == newItem
        }

    }
}