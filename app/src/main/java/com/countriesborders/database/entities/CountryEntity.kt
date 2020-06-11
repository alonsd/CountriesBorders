package com.countriesborders.database.entities

import androidx.room.Entity
import com.countriesborders.util.Constants
import com.countriesborders.util.Constants.Tables.countriesTable

@Entity(tableName = countriesTable, primaryKeys = ["countryName"])
class CountryEntity(
    val countryName: String,
    val nativeName: String,
    val area: Double,
    val cioc: String? = null,
    val imageUri : String? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CountryEntity

        if (countryName != other.countryName) return false
        if (nativeName != other.nativeName) return false
        if (area != other.area) return false
        if (cioc != other.cioc) return false
        if (imageUri != other.imageUri) return false

        return true
    }

    override fun hashCode(): Int {
        var result = countryName.hashCode()
        result = 31 * result + nativeName.hashCode()
        result = 31 * result + area.hashCode()
        result = 31 * result + (cioc?.hashCode() ?: 0)
        result = 31 * result + (imageUri?.hashCode() ?: 0)
        return result
    }
}
