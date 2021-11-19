package com.example.moviemanager.Features.Detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemanager.Model.FavoriteMovies
import com.example.moviemanager.Model.MovieDetails
import com.example.moviemanager.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val liveData = MutableLiveData<MovieDetails>()

    fun searchMovieById(omdbId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchMovieById(omdbId)
            liveData.postValue(result)
        }
    }

    fun saveMovie(movieInfo: FavoriteMovies) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.db.movieDao().saveMovieInfo(movieInfo)
        }
    }

    suspend fun isSavedMovie(imdbId: String): Boolean {
        return repository.db.movieDao().isMovieSaved(imdbId)
    }

    fun unsaveMovie(favMovie: FavoriteMovies) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.db.movieDao().unSaveMovieInfo(favMovie)

        }
    }
}