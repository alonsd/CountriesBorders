package com.countriesborders.network

import com.countriesborders.model.CountriesModel
import retrofit2.Call
import retrofit2.http.GET

interface CountriesApi {


    @GET("rest/v2/all")
    fun getAllCountries() : Call<CountriesModel>
}