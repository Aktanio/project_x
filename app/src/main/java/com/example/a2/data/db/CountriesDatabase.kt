package com.example.a2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a2.data.CountryEntity

@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CountriesDatabase: RoomDatabase() {

    abstract fun getCountriesDao(): CountriesDao

    companion object{
        @Volatile
        private var INSTANCE: CountriesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CountriesDatabase::class.java,
                "countries_db"
            ).build()
    }
}