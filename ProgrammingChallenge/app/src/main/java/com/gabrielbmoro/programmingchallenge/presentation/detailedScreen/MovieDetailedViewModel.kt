package com.gabrielbmoro.programmingchallenge.presentation.detailedScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMovieUseCase
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult

class MovieDetailedViewModel(private val favoriteMovieUseCase: FavoriteMovieUseCase) : ViewModel() {

    private var movie: Movie? = null

    fun setup(movie: Movie): LiveData<ViewModelResult> {
        this.movie = movie
        return liveData {
            try {
                movie.isFavorite = favoriteMovieUseCase.isFavorite(movie)
                emit(ViewModelResult.Success)
            } catch (exception: Exception) {
                emit(ViewModelResult.Error)
            }
        }
    }

    fun favoriteEvent(isToFavorite: Boolean): LiveData<ViewModelResult>? {
        return movie?.let { m ->
            liveData {
                try {
                    if (isToFavorite)
                        favoriteMovieUseCase.favoriteMovie(m)
                    else
                        favoriteMovieUseCase.unFavoriteMovie(m)
                    movie?.isFavorite = isToFavorite
                    emit(ViewModelResult.Success)
                } catch (exception: Exception) {
                    emit(ViewModelResult.Error)
                }
            }
        }
    }

    fun getMovie() = movie
}