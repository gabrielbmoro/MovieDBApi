package com.gabrielbmoro.programmingchallenge.koin.api

import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.model.Movie
import com.gabrielbmoro.programmingchallenge.model.MoviesListType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ApiRepositoryImpl(val service: ApiRepository) {

    fun getTopRatedMovies(scope: CoroutineScope, contract : (movies:List<Movie>)-> Unit) {
        scope.launch {
            val pageResult = service.getMovies(BuildConfig.token, MoviesListType.TOP_RATED_MOVIES.requestParameter)
            contract.invoke(pageResult.results ?: emptyList())
        }
    }

    fun getPopularMovies(scope : CoroutineScope, contract: (movies: List<Movie>) -> Unit) {
        scope.launch {
            val pageResult =service.getMovies(BuildConfig.token, MoviesListType.POPULAR_RATED_MOVIES.requestParameter)
            contract.invoke(pageResult.results ?: emptyList())
        }
    }
}
