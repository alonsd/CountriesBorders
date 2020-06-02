package com.countriesborders.database.entities

import androidx.room.Entity
import com.countriesborders.util.Constants.Tables.countriesBorder

@Entity(tableName = countriesBorder, primaryKeys = ["selectedCountry"])
class  CountryBorderEntity (
    val selectedCountry : String,
    val neighborCountries : List<String>
)