package com.gabrielbmoro.programmingchallenge.koin.api

import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.model.MoviesListType
import com.gabrielbmoro.programmingchallenge.model.Page
import rx.Observable

class ApiRepositoryImpl(val service: ApiRepository) {

    fun getTopRatedMovies() : Observable<Page> {
        return service.getMovies(BuildConfig.token, MoviesListType.TOP_RATED_MOVIES.requestParameter)
    }

    fun getPopularMovies() : Observable<Page> {
        return service.getMovies(BuildConfig.token, MoviesListType.POPULAR_RATED_MOVIES.requestParameter)
    }
}
