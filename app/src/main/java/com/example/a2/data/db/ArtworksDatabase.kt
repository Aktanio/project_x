package com.example.a2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a2.data.ArtworkEntity

@Database(entities = [ArtworkEntity::class], version = 3)
abstract class ArtworksDatabase: RoomDatabase() {

    abstract fun getArtworksDao(): ArtworksDao

    companion object{
        @Volatile
        private var INSTANCE: ArtworksDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArtworksDatabase::class.java,
                "art_db"
            ).build()
    }
}