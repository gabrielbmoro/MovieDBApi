package com.gabrielbmoro.programmingchallenge.presentation.detailedScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMovieUseCase
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import kotlinx.coroutines.launch

class MovieDetailedViewModel(
        val movie: Movie,
        private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModel() {

    val onFavoriteMovieEvent = MutableLiveData<ViewModelResult>()

    init {
        viewModelScope.launch {
            try {
                movie.isFavorite = favoriteMovieUseCase.isFavorite(movie)
                onFavoriteMovieEvent.postValue(ViewModelResult.Success)
            } catch (exception: Exception) {
                onFavoriteMovieEvent.postValue(ViewModelResult.Error)
            }
        }
    }

    fun favoriteEvent(isToFavorite: Boolean) {
        viewModelScope.launch {
            try {
                if (isToFavorite)
                    favoriteMovieUseCase.favoriteMovie(movie)
                else
                    favoriteMovieUseCase.unFavoriteMovie(movie)
                movie.isFavorite = isToFavorite
                onFavoriteMovieEvent.postValue(ViewModelResult.Success)
            } catch (exception: Exception) {
                onFavoriteMovieEvent.postValue(ViewModelResult.Error)
            }
        }
    }
}