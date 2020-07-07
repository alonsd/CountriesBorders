package com.countriesborders.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.countriesborders.R
import com.countriesborders.adapter.CountriesListAdapter
import com.countriesborders.adapter.CountryViewHolder
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.database.repository.CountriesRepository
import com.countriesborders.viewmodel.CountriesListViewModel
import kotlinx.android.synthetic.main.fragment_countries_list.fragment_countries_list_countries_recyclerview as countriesRecyclerview
import kotlinx.android.synthetic.main.fragment_countries_list.fragment_countries_list_toolbar as toolbar

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {


    private lateinit var countriesViewModel: CountriesListViewModel
    private lateinit var countriesAdapter: CountriesListAdapter
    private var countriesList = mutableListOf<CountryEntity>()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.showOverflowMenu()
        initViewModel()
        initData()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.country_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (countriesList.isEmpty())
            return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.country_list_menu_order_by_country_name_ascending -> {
                countriesList.sortBy { it.nativeName }
            }

            R.id.country_list_menu_order_by_country_name_descending -> {
                countriesList.sortByDescending { it.nativeName }
            }

            R.id.country_list_menu_order_by_area_ascending -> {
                countriesList.sortBy { it.area }
            }

            R.id.country_list_menu_order_by_area_descending -> {
                countriesList.sortByDescending { it.area }
            }
        }
        countriesAdapter.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        countriesViewModel = ViewModelProvider(this).get(CountriesListViewModel::class.java)
        countriesViewModel.fetchAllCountries(object : CountriesRepository.CountryFetchCallback {
            override fun onFetchError(reason: String) {
                Toast.makeText(context, reason, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initData() {
        countriesAdapter = CountriesListAdapter(countriesList, object : CountryViewHolder.OnCountryClickListener {
            override fun onCountryClicked(countryName: String) {

                val action = CountriesListFragmentDirections.actionCountriesListFragmentToCountryBordersFragment()


                navController.navigate(
                    R.id.action_countriesListFragment_to_countryBordersFragment,
                    bundleOf(Pair(getString(R.string.countries_list_fragment_selected_country), countryName))
                )
            }
        })
        countriesRecyclerview.adapter = countriesAdapter
        countriesRecyclerview.setHasFixedSize(true)
        countriesRecyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        countriesViewModel.getAllCountries().observe(requireActivity(), Observer { countryList ->
            countriesList.addAll(countryList)
            countriesAdapter.notifyDataSetChanged()
        })
    }
}