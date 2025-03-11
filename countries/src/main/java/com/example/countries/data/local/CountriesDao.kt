package com.example.countries.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countries.data.local.model.CountryDBEntity

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countries")
    suspend fun getAllCachedCountries(): List<CountryDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(country: List<CountryDBEntity>)
}