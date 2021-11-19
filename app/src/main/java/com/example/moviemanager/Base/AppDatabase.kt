package com.example.moviemanager.Base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviemanager.Model.FavoriteMovies
import com.example.moviemanager.Repository.MovieInfoDao

@Database(entities = [FavoriteMovies::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieInfoDao
}