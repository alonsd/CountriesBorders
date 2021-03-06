package com.countriesborders.util

import com.countriesborders.database.AppDatabase
import com.countriesborders.database.dao.CountryBorderDao
import com.countriesborders.database.dao.CountryDao
import com.countriesborders.database.repository.CountriesRepository
import com.countriesborders.viewmodel.CountriesListViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {

    object Repository {
        private var countriesRepositoryInstance = CountriesRepository()
        val countriesRepository: CountriesRepository?
            get() = countriesRepositoryInstance
    }

    object Database {
        private val database = AppDatabase.getDatabaseInstance()
        val countryBorderDao: CountryBorderDao
            get() = database.countryBorderDao()
        val countryDao: CountryDao
            get() = database.countryDao()
    }

    object Tables {
        const val countriesTable = "CountriesTable"
        const val countriesBorder = "CountriesBordersTable"
        const val databaseName = "ApplicationDatabase"
    }


    object Networking {
        private const val countriesBaseUrl = "https://restcountries.eu/"
        val countriesRetrofitObject: Retrofit = Retrofit.Builder()
            .baseUrl(countriesBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}