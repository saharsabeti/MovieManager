package com.example.moviemanager.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favMovies")
data class FavoriteMovies(
    @PrimaryKey val imdbId: String,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "actors") val actors: String?,
    @ColumnInfo(name = "plot") val plot: String?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "awards") val awards: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "director") val director: String?,
    @ColumnInfo(name = "genre") val genre: String?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "metascore") val metascore: String?,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "rated") val rated: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "runtime") val runtime: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "writer") val writer: String?,
    @ColumnInfo(name = "imdb_rating") val imdbRating: String?,
    @ColumnInfo(name = "imdb_votes") val imdbVotes: String?,
    @ColumnInfo(name = "total_seasons") val totalSeasons: String?
)
