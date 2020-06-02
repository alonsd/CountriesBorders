package com.countriesborders.network

import com.countriesborders.R
import com.countriesborders.model.CountriesModel
import com.countriesborders.util.App
import com.countriesborders.util.Constants.Networking.countriesRetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CountriesNetworking {

    private val countriesApi = countriesRetrofitObject.create(CountriesApi::class.java)

    fun getAllCountries(callback : CountriesResponseCallback) {
        val call = countriesApi.getAllCountries()
        call.enqueue(object : Callback<CountriesModel> {
            override fun onResponse(call: Call<CountriesModel>, response: Response<CountriesModel>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onError(App.context!!.getString(R.string.countries_general_error_message))
                }
            }

            override fun onFailure(call: Call<CountriesModel>, throwable: Throwable) {
                throwable.localizedMessage?.let { reason ->
                    callback.onError(reason)
                }

            }
        })
    }


    interface CountriesResponseCallback{
        fun onSuccess(model : CountriesModel)
        fun onError(reason : String)
    }
}