package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository

class FavoriteMovieUseCase(val repository: MoviesRepository) {

    suspend fun favoriteMovie(movie: Movie): Boolean {
        return repository.doAsFavorite(movie)
    }

    suspend fun unFavoriteMovie(movie: Movie): Boolean {
        return repository.unFavorite(movie)
    }
}