package com.countriesborders.model

import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity

class CountriesModel : ArrayList<CountriesModel.CountriesModelItem>() {

    companion object {

        fun toCountryEntity(model: CountriesModelItem): CountryEntity {
            val name = model.name
            val nativeName = model.nativeName
            val area = model.area
            val cioc = model.cioc
            return CountryEntity(name, nativeName, area, cioc)
        }

        fun toCountryBorderEntity(model : CountriesModelItem) : CountryBorderEntity {
            val name = model.name
            val borders = model.borders
            return CountryBorderEntity(name, borders)
        }
    }


    data class CountriesModelItem(
        val alpha2Code: String,
        val alpha3Code: String,
        val altSpellings: List<String>,
        val area: Double,
        val borders: List<String>,
        val callingCodes: List<String>,
        val capital: String,
        val cioc: String?,
        val currencies: List<Currency>,
        val demonym: String,
        val flag: String?,
        val gini: Double,
        val languages: List<Language>,
        val latlng: List<Double>,
        val name: String,
        val nativeName: String,
        val numericCode: String,
        val population: Int,
        val region: String,
        val regionalBlocs: List<RegionalBloc>,
        val subregion: String,
        val timezones: List<String>,
        val topLevelDomain: List<String>,
        val translations: Translations
    ) {
        data class Currency(
            val code: String,
            val name: String,
            val symbol: String
        )

        data class Language(
            val iso639_1: String,
            val iso639_2: String,
            val name: String,
            val nativeName: String
        )

        data class RegionalBloc(
            val acronym: String,
            val name: String,
            val otherAcronyms: List<Any>,
            val otherNames: List<String>
        )

        data class Translations(
            val br: String,
            val de: String,
            val es: String,
            val fa: String,
            val fr: String,
            val hr: String,
            val it: String,
            val ja: String,
            val nl: String,
            val pt: String
        )
    }
}