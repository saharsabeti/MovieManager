package com.example.moviemanager.Features.Favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemanager.Model.FavoriteMovies
import com.example.moviemanager.Repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val liveData = MutableLiveData<List<FavoriteMovies>>()

    suspend fun getFavMovies() {
        liveData.postValue(repository.db.movieDao().getAllMovies())
    }

}