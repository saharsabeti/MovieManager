package com.example.moviemanager.Base.di

import androidx.room.Room
import com.example.moviemanager.Base.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "movie"
        ).allowMainThreadQueries().build()

    }
}