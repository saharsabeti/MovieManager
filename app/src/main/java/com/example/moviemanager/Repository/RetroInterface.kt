package com.example.moviemanager.Repository

import com.example.moviemanager.Model.MovieDetails
import com.example.moviemanager.Model.MovieSearch
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY: String = "67dc30b"

interface RetroInterface {
    @GET("/")
    suspend fun searchMovieByTitle(
        @Query("s") name: String,
        @Query("apikey") apiKey: String = APIKEY
    ): MovieSearch

    @GET("/")
    suspend fun searchMovieByID(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = APIKEY
    ): MovieDetails
}