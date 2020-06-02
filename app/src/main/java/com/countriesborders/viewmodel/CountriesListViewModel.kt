package com.countriesborders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.database.repository.CountriesRepository
import com.countriesborders.util.Constants
import com.countriesborders.util.Constants.Repository.countriesRepository

class CountriesListViewModel : ViewModel() {


    fun fetchAllCountries(callback : CountriesRepository.CountryFetchCallback) {
        countriesRepository?.fetchAllCountries(callback)
    }

    fun getAllCountries() : LiveData<List<CountryEntity>>{
        return countriesRepository!!.getAllCountries()
    }

    fun getCountryBorders(countryName : String) : LiveData<CountryBorderEntity>{
        return countriesRepository!!.getAllCountryBorders(countryName)
    }

    fun getCountryByCioc(ciocList: List<String>): LiveData<List<CountryEntity>> {
        return countriesRepository!!.getCountryByCioc(ciocList)
    }

}