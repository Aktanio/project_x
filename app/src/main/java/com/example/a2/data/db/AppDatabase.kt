package com.example.a2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a2.data.ArtworkDBEntity
import com.example.a2.data.CountryDBEntity

@Database(entities = [ArtworkDBEntity::class, CountryDBEntity::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getArtworksDao(): ArtworksDao
    abstract fun getCountryDao(): CountriesDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "main_db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}