package com.gabrielbmoro.programmingchallenge.model

import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.koin.api.ApiRepository
import com.gabrielbmoro.programmingchallenge.koin.dataBase.FavoriteMoviesDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MoviesRepository(val service: ApiRepository, val favoriteDAO: FavoriteMoviesDAO) {

    fun getAllFavoriteMovies(scope : CoroutineScope, contract: (movies: List<Movie>) -> Unit) {
        scope.launch {
            contract.invoke(getFavoriteMovies())
        }
    }

    fun getTopRatedMovies(scope: CoroutineScope, contract: (movies: List<Movie>) -> Unit) {
        scope.launch {
            val favoriteMovies = getFavoriteMovies()
            contract.invoke(
                    fillFavoriteMovies(
                            fromServer = service.getMovies(
                                    BuildConfig.token,
                                    MoviesListType.TOP_RATED_MOVIES.requestParameter
                            ).results ?: emptyList(),
                            favoriteMovies = favoriteMovies
                    )
            )
        }
    }

    private suspend fun getFavoriteMovies(): List<Movie> {
        return favoriteDAO.allFavoriteMovies().map {
            it.isFavorite = true
            it
        }
    }

    fun getPopularMovies(scope: CoroutineScope, contract: (movies: List<Movie>) -> Unit) {
        scope.launch {
            val favoriteMovies = getFavoriteMovies()
            contract.invoke(
                    fillFavoriteMovies(
                            fromServer = service.getMovies(
                                    BuildConfig.token,
                                    MoviesListType.POPULAR_RATED_MOVIES.requestParameter
                            ).results ?: emptyList(),
                            favoriteMovies = favoriteMovies
                    )
            )
        }
    }

    fun saveMovieAsFavorite(coroutineScope: CoroutineScope, movie : Movie, contract: () -> Unit) {
        coroutineScope.launch {
            movie.isFavorite = true
            favoriteDAO.saveFavorite(movie)
            contract.invoke()
        }
    }

    fun unFavorite(coroutineScope: CoroutineScope, movie : Movie, contract: () -> Unit) {
        coroutineScope.launch {
            movie.isFavorite = false
            favoriteDAO.removeFavorite(movie.id)
            contract.invoke()
        }
    }

    private fun fillFavoriteMovies(fromServer: List<Movie>, favoriteMovies: List<Movie>): List<Movie> {
        return fromServer.map {
            it.apply {
                isFavorite = favoriteMovies.contains(it)
            }
        }
    }
}