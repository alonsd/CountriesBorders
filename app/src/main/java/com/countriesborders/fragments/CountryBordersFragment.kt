package com.countriesborders.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.countriesborders.R
import com.countriesborders.adapter.CountriesListAdapter
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.observeOnce
import com.countriesborders.viewmodel.CountriesListViewModel
import kotlinx.android.synthetic.main.fragment_country_borders.fragment_country_borders_back_button as backButton
import kotlinx.android.synthetic.main.fragment_country_borders.fragment_country_borders_borders_with as bordersWith
import kotlinx.android.synthetic.main.fragment_country_borders.fragment_country_borders_countries_recyclerview as countriesRecyclerView
import kotlinx.android.synthetic.main.fragment_country_borders.fragment_country_borders_country_name as countryName


class CountryBordersFragment : Fragment(R.layout.fragment_country_borders) {

    private lateinit var selectedCountry : String
    private lateinit var countriesViewModel: CountriesListViewModel
    private lateinit var countriesAdapter : CountriesListAdapter
    private var countriesList = mutableListOf<CountryEntity>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesViewModel = ViewModelProvider(this).get(CountriesListViewModel::class.java)
        initData()
        initClickListener()
    }

    private fun initClickListener() {
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initData() {
        countriesAdapter = CountriesListAdapter(countriesList, null)
        countriesRecyclerView.setHasFixedSize(true)
        countriesRecyclerView.layoutManager = LinearLayoutManager(context)
        countriesRecyclerView.adapter = countriesAdapter

        arguments?.let {
            selectedCountry = it.getString(getString(R.string.countries_list_fragment_selected_country))!!
        }
        countryName.text = selectedCountry
        countriesViewModel.getCountryBorders(selectedCountry).observeOnce(requireActivity(), Observer {countryBorder ->
            if (countryBorder.neighborCountries.isEmpty()) {
                bordersWith.text = getString(R.string.country_border_fragment_country_does_not_have_borders)
                return@Observer
            }
            countriesViewModel.getCountryByCioc(countryBorder.neighborCountries).observeOnce(requireActivity(), Observer {countryEntityList ->
                countriesList.addAll(countryEntityList)
                countriesAdapter.notifyDataSetChanged()
            })
        })
    }
}