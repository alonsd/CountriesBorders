package com.countriesborders.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countriesborders.R
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.App

class CountriesListAdapter(private val countriesList: List<CountryEntity>, private val callback: CountryViewHolder.OnCountryClickListener?) : RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(App.context!!).inflate(R.layout.country_viewholder, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = countriesList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countriesList[position], callback)
    }

}