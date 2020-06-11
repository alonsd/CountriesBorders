package com.countriesborders.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.Constants
import com.countriesborders.util.Constants.Tables.countriesTable

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: CountryEntity)

//    @Query("select * from $countriesTable")
//    fun getAllCountries(): LiveData<List<CountryEntity>>

    @Query("select * from $countriesTable")
    fun getAllCountries(): DataSource.Factory<Int, CountryEntity>

//    @Query("select * from $countriesTable where cioc in (:ciocList)")
//    fun getCountryByCioc(ciocList: List<String>): LiveData<List<CountryEntity>>

    @Query("select * from $countriesTable where cioc in (:ciocList)")
    fun getCountryByCioc(ciocList: List<String>): DataSource.Factory<Int, CountryEntity>

}