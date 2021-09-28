package com.luke.codejpmc.DI

import android.app.Application
import androidx.room.Room
import com.luke.codejpmc.database.AlbumDatabase

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun providesDatabase(application: Application): AlbumDatabase {
        return Room.databaseBuilder(application, AlbumDatabase::class.java, "albums.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { providesDatabase(androidApplication()) }
}