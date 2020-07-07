package com.countriesborders.database.entities

import androidx.room.Entity
import com.countriesborders.util.Constants
import com.countriesborders.util.Constants.Tables.countriesTable

@Entity(tableName = countriesTable, primaryKeys = ["countryName"])
class CountryEntity(
    val countryName: String,
    val nativeName: String,
    val area: Double,
    val cioc: String? = null
)