package com.countriesborders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.database.repository.CountriesRepository

class CountriesListViewModel : ViewModel() {

    private var countriesRepository = CountriesRepository(this)


    fun fetchAllCountries(callback: CountriesRepository.CountryFetchCallback) {
        countriesRepository.fetchAllCountries(callback)
    }

    fun getAllCountries(comparator: Comparator<in CountryEntity>?): LiveData<PagingData<CountryEntity>> {
        return countriesRepository.getAllCountries(comparator)
    }

    fun getCountryBorders(countryName: String): LiveData<CountryBorderEntity> {
        return countriesRepository.getAllCountryBorders(countryName)
    }

    fun getCountryByCioc(ciocList: List<String>): LiveData<PagedList<CountryEntity>> {
        return countriesRepository.getCountryByCioc(ciocList)
    }

}