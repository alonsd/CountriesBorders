package com.countriesborders.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.Constants
import com.countriesborders.util.Constants.Tables.countriesBorder

@Dao
interface CountryBorderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CountryBorderEntity)

    @Query("select * from $countriesBorder where selectedCountry =:selectedCountry")
    fun getAllCountriesBorders(selectedCountry: String): LiveData<CountryBorderEntity>
}