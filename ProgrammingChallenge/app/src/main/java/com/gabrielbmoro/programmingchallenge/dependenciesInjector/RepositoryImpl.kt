package com.gabrielbmoro.programmingchallenge.dependenciesInjector

import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.model.Page
import rx.Observable

/**
 * This class creates the api service.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class RepositoryImpl(val service: ApiRepository) {

    /**
     * Return the movies using the retrofit library.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun getMovies(a_strSortedBy: String): Observable<Page> {
        return service.getMovies(BuildConfig.token, a_strSortedBy)
    }
}
