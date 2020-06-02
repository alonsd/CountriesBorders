package com.countriesborders.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AppDatabaseConverter {


    private val countryNeighborListType: Type = object : TypeToken<List<String?>?>() {}.type
    private val gson = Gson()


    @TypeConverter
    fun fromStringToNeighborCountryList(value: String): List<String>? {
        return gson.fromJson(value, countryNeighborListType)
    }

    @TypeConverter
    fun stringToCountryNeighborList(models: List<String>?): String? {
        return gson.toJson(models, countryNeighborListType)
    }

}