package com.gabrielbmoro.programmingchallenge.repository

import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.repository.api.response.PageResponse

interface MoviesRepository {

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun getPopularMovies(pageNumber: Int): PageResponse?

    suspend fun getTopRatedMovies(pageNumber: Int): PageResponse?

    suspend fun doAsFavorite(movie: Movie): Boolean

    suspend fun unFavorite(movie: Movie): Boolean

    suspend fun checkIsAFavoriteMovie(movie: Movie): Boolean
}