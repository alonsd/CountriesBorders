package com.countriesborders.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.model.CountriesModel
import com.countriesborders.network.CountriesNetworking
import com.countriesborders.util.Constants.Database.countryBorderDao
import com.countriesborders.util.Constants.Database.countryDao
import com.countriesborders.util.sortBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesRepository(val viewmodel: ViewModel) {

    fun fetchAllCountries(callback: CountryFetchCallback) {
        CountriesNetworking.getAllCountries(object : CountriesNetworking.CountriesResponseCallback {
            override fun onSuccess(model: CountriesModel) {
                model.forEach { countryModelItem ->
                    val countryEntity = CountriesModel.toCountryEntity(countryModelItem, viewmodel)
                    val countryBorderEntity = CountriesModel.toCountryBorderEntity(countryModelItem)
                    viewmodel.viewModelScope.launch(Dispatchers.IO) { countryBorderDao.insert(countryBorderEntity) }
                    viewmodel.viewModelScope.launch(Dispatchers.IO) { countryDao.insert(countryEntity) }
                }
            }

            override fun onError(reason: String) {
                callback.onFetchError(reason)
            }
        })
    }

    fun getAllCountries(comparator: Comparator<in CountryEntity>?): LiveData<PagedList<CountryEntity>> {
        if (comparator == null)
            return countryDao.getAllCountries().toLiveData(10)
        return countryDao.getAllCountries().sortBy(comparator).toLiveData(10)
    }

    fun getAllCountryBorders(selectedCountry: String): LiveData<CountryBorderEntity> {
        return countryBorderDao.getAllCountriesBorders(selectedCountry)
    }

    fun getCountryByCioc(ciocList: List<String>): LiveData<PagedList<CountryEntity>> {
        return countryDao.getCountryByCioc(ciocList).toLiveData(10)
    }

    interface CountryFetchCallback {
        fun onFetchError(reason: String)
    }
}