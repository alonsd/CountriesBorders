package com.countriesborders.database.repository

import androidx.lifecycle.LiveData
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.model.CountriesModel
import com.countriesborders.network.CountriesNetworking
import com.countriesborders.util.Constants.Database.countryBorderDao
import com.countriesborders.util.Constants.Database.countryDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CountriesRepository {

    fun fetchAllCountries(callback: CountryFetchCallback) {
        CountriesNetworking.getAllCountries(object : CountriesNetworking.CountriesResponseCallback {
            override fun onSuccess(model: CountriesModel) {
                model.forEach { countryModelItem ->
                    val countryBorderEntity = CountriesModel.toCountryBorderEntity(countryModelItem)
                    val countryEntity = CountriesModel.toCountryEntity(countryModelItem)
                    GlobalScope.launch { countryBorderDao.insert(countryBorderEntity) }
                    GlobalScope.launch { countryDao.insert(countryEntity) }
                }
            }

            override fun onError(reason: String) {
                callback.onFetchError(reason)
            }
        })
    }

    fun getAllCountries(): LiveData<List<CountryEntity>> {
        return countryDao.getAllCountries()
    }

    fun getAllCountryBorders(selectedCountry: String): LiveData<CountryBorderEntity> {
        return countryBorderDao.getAllCountriesBorders(selectedCountry)
    }

    fun getCountryByCioc(ciocList: List<String>): LiveData<List<CountryEntity>> {
        return countryDao.getCountryByCioc(ciocList)
    }

    interface CountryFetchCallback {
        fun onFetchError(reason: String)
    }
}