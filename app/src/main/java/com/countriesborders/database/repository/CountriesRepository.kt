package com.countriesborders.database.repository

import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.toLiveData
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.model.CountriesModel
import com.countriesborders.network.CountriesNetworking
import com.countriesborders.util.Constants.Database.countryBorderDao
import com.countriesborders.util.Constants.Database.countryDao
import com.countriesborders.util.sortBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
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

    fun getAllCountries(comparator: Comparator<in CountryEntity>?): LiveData<PagingData<CountryEntity>> {
        if (comparator == null)
            return countryDao.getAllCountries().flow.asLiveData()
//        return countryDao.getAllCountries().sortBy(comparator).toLiveData(10)
        return countryDao.getAllCountries().flow.asLiveData()

    }

    fun getAllCountryBorders(selectedCountry: String): LiveData<CountryBorderEntity> {
        return countryBorderDao.getAllCountriesBorders(selectedCountry)
    }

    fun getCountryByCioc(ciocList: List<String>): LiveData<PagingData<CountryEntity>> {
        return countryDao.getCountryByCioc(ciocList).flow.asLiveData()
    }

    interface CountryFetchCallback {
        fun onFetchError(reason: String)
    }
}