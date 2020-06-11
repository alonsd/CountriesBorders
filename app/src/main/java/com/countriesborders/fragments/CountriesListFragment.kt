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
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.countriesborders.R
import com.countriesborders.adapter.CountriesListAdapter
import com.countriesborders.adapter.CountryViewHolder
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.database.repository.CountriesRepository
import com.countriesborders.viewmodel.CountriesListViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.fragment_countries_list.fragment_countries_list_countries_recyclerview as countriesRecyclerview
import kotlinx.android.synthetic.main.fragment_countries_list.fragment_countries_list_toolbar as toolbar

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {


    private lateinit var countriesViewModel: CountriesListViewModel
    private lateinit var countriesAdapter: CountriesListAdapter
    private var countriesList: PagedList<CountryEntity>? = null
    private lateinit var navController: NavController
    private val testViewModel by viewModel<CountriesListViewModel>()

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
        var comparator: Comparator<CountryEntity>? = null
        when (item.itemId) {
            R.id.country_list_menu_order_by_country_name_ascending -> {
                comparator = compareBy { country -> country.nativeName }

            }

            R.id.country_list_menu_order_by_country_name_descending -> {
                comparator = compareByDescending { country -> country.nativeName }
            }

            R.id.country_list_menu_order_by_area_ascending -> {
                comparator = compareBy { country -> country.area }
            }

            R.id.country_list_menu_order_by_area_descending -> {
                comparator = compareByDescending { country -> country.area }
            }
        }
        countriesViewModel.getAllCountries(comparator).observe(this, Observer { list ->
            countriesAdapter.submitList(list)
            countriesAdapter.notifyDataSetChanged()
        })
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
        countriesAdapter = CountriesListAdapter(object : CountryViewHolder.OnCountryClickListener {
            override fun onCountryClicked(countryName: String) {
                navController.navigate(
                    R.id.action_countriesListFragment_to_countryBordersFragment,
                    bundleOf(Pair(getString(R.string.countries_list_fragment_selected_country), countryName))
                )
            }
        })
        countriesRecyclerview.adapter = countriesAdapter
        countriesRecyclerview.setHasFixedSize(true)
        countriesRecyclerview.itemAnimator = null
        countriesRecyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        countriesViewModel.getAllCountries(null).observe(requireActivity(), Observer { list ->
            countriesAdapter.submitList(list)
            countriesList = list
        })

    }
}