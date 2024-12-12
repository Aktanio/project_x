package com.example.a2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a2.data.CountryEntity

@Dao
interface CountriesDao {
    @Query("SELECT * FROM countries")
    suspend fun getAllCachedCountries(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(country: List<CountryEntity>)
}