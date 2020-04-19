package com.gabrielbmoro.programmingchallenge.repository.api

import com.gabrielbmoro.programmingchallenge.core.ConfigVariables
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.repository.api.response.PageResponse

class ApiRepositoryImpl(private val apiRepository : ApiRepository) : MoviesRepository {

    override suspend fun getFavoriteMovies() = emptyList<Movie>()

    override suspend fun getPopularMovies(): PageResponse {
        return apiRepository.getMovies(
                    apiKey = ConfigVariables.TOKEN,
                    sortBy = ConfigVariables.PARAMETER_POPULAR_MOVIES
            )
        }

    override suspend fun getTopRatedMovies(): PageResponse {
        return apiRepository.getMovies(
                    apiKey = ConfigVariables.TOKEN,
                    sortBy = ConfigVariables.PARAMETER_TOP_RATED_MOVIES
            )
    }

    override suspend fun doAsFavorite(movie: Movie) = false

    override suspend fun unFavorite(movie: Movie)= false

}