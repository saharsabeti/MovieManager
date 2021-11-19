package com.example.moviemanager.Repository

import com.example.moviemanager.Base.AppDatabase
import com.example.moviemanager.Model.MovieDetails
import com.example.moviemanager.Model.MovieSearch
import javax.inject.Inject

class Repository @Inject constructor(val db: AppDatabase, val network: RetroInterface) {
    suspend fun searchMovieByTitle(title: String): MovieSearch {
        return network.searchMovieByTitle(title)
    }

    suspend fun searchMovieById(omdbId: String): MovieDetails {
        return network.searchMovieByID(omdbId)
    }
}