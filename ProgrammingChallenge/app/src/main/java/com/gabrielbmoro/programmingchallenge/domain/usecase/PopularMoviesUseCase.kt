package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.domain.usecase.mapper.MoviesMapper
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository

class PopularMoviesUseCase(repository: MoviesRepository) : BaseUseCase(repository) {

    suspend fun execute(): List<Movie> {
        return MoviesMapper.map(repository.getPopularMovies())
    }
}