package com.countriesborders.adapter

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.countriesborders.R
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.App
import kotlinx.android.synthetic.main.country_viewholder.view.*

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val rootLayout: LinearLayout = view.country_viewholder_root_layout
    private val nativeName: TextView = view.country_viewholder_native_name
    private val countryName: TextView = view.country_viewholder_country_name
    private val area: TextView = view.country_viewholder_area

    fun bind(model: CountryEntity, callback: OnCountryClickListener?) {
        nativeName.text = App.context!!.getString(R.string.country_view_holder_native_name).plus(" ${model.countryName}")
        countryName.text = App.context!!.getString(R.string.country_view_holder_country_name).plus(" ${model.nativeName}")
        area.text = App.context!!.getString(R.string.country_view_holder_country_area).plus(" ${model.area}")
        rootLayout.setOnClickListener {
            callback?.let {
                it.onCountryClicked(model.countryName)
            }
        }
    }

    interface OnCountryClickListener {
        fun onCountryClicked(countryName: String)
    }
}