package com.countriesborders.database.dao

import androidx.paging.Pager
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.Constants.Tables.countriesTable

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: CountryEntity)

    @Query("select * from $countriesTable")
    fun getAllCountries(): Pager<Int, CountryEntity>

    @Query("select * from $countriesTable where cioc in (:ciocList)")
    fun getCountryByCioc(ciocList: List<String>): Pager<Int, CountryEntity>

}