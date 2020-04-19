package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository

class FavoriteMoviesUseCase(repository: MoviesRepository) : BaseUseCase(repository) {
    suspend fun execute(): List<Movie> {
        return repository.getFavoriteMovies()
    }
}