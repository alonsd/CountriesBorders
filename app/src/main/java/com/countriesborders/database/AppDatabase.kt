package com.countriesborders.database

import androidx.room.*
import com.countriesborders.database.converter.AppDatabaseConverter
import com.countriesborders.database.dao.CountryBorderDao
import com.countriesborders.database.dao.CountryDao
import com.countriesborders.database.entities.CountryBorderEntity
import com.countriesborders.database.entities.CountryEntity
import com.countriesborders.util.App
import com.countriesborders.util.Constants
import com.countriesborders.util.Constants.Tables.databaseName

@Database(entities = [CountryEntity::class, CountryBorderEntity::class], version = 1, exportSchema = false)
@TypeConverters(AppDatabaseConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
    abstract fun countryBorderDao(): CountryBorderDao

    companion object {

        private var databaseInstance: AppDatabase? = null

        @Synchronized
        fun getDatabaseInstance(): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(App.context!!, AppDatabase::class.java, databaseName).fallbackToDestructiveMigration().build()
            }
            return databaseInstance!!
        }
    }
}