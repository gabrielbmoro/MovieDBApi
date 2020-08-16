package com.gabrielbmoro.programmingchallenge.presentation.detailedScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMovieUseCase
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import kotlinx.coroutines.launch

class MovieDetailedViewModel(private val favoriteMovieUseCase: FavoriteMovieUseCase) : ViewModel() {

    private var movie: Movie? = null
    val onFavoriteMovieEvent = MutableLiveData<ViewModelResult>()

    fun setup(movie: Movie) {
        this.movie = movie
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
        movie?.let { m ->
            viewModelScope.launch {
                try {
                    if (isToFavorite)
                        favoriteMovieUseCase.favoriteMovie(m)
                    else
                        favoriteMovieUseCase.unFavoriteMovie(m)
                    movie?.isFavorite = isToFavorite
                    onFavoriteMovieEvent.postValue(ViewModelResult.Success)
                } catch (exception: Exception) {
                    onFavoriteMovieEvent.postValue(ViewModelResult.Error)
                }
            }
        }
    }

    fun getMovie() = movie
}