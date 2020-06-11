package com.countriesborders.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.App
import com.countriesborders.util.ImageRequestListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class CountriesModel : ArrayList<CountriesModel.CountriesModelItem>() {

    companion object {

        fun toCountryEntity(model: CountriesModelItem, viewmodel: ViewModel): CountryEntity {
            val name = model.name
            val nativeName = model.nativeName
            val area = model.area
            val cioc = model.cioc
            val flag = model.flag


//            flag = "https://www.sciencemag.org/sites/default/files/styles/article_main_image_-_1280w__no_aspect_/public/dogs_1280p_0.jpg?itok=6jQzdNB8"
//            val countriesDirectory = File(App.context!!.cacheDir, "Countries")
//            if (!countriesDirectory.exists()) {
//                countriesDirectory.mkdir()
//            }
//            val countryDirectory = File(countriesDirectory, model.name)
//            if (!countryDirectory.exists()) {
//                countryDirectory.mkdir()
//
//            }
//            val countryImage = File(countryDirectory, model.name.plus("_image"))
//            if (!countryImage.exists()) {
//                countryImage.createNewFile()
//            }
//            Glide.with(App.context!!).asBitmap().load(flag).into(object : CustomTarget<Bitmap>() {
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                    viewmodel.viewModelScope.launch(Dispatchers.IO) {
//                        val fileOutputStream = FileOutputStream(countryImage)
//                        resource.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
//                        fileOutputStream.flush()
//                        fileOutputStream.close()
//                        val absolutePath = countryImage.absolutePath
//                        Log.d("absolutePath", absolutePath)
//                    }
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//
//                }
//            })
            return CountryEntity(name, nativeName, area, cioc, flag)
        }

        fun toCountryBorderEntity(model: CountriesModelItem): CountryBorderEntity {
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