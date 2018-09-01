package com.gabrielbmoro.programmingchallenge.api

import com.gabrielbmoro.programmingchallenge.models.Page
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * The inferface that defines the requests to API.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
interface ApiServiceInterface {

    /**
     * This abstract method provides the implementation
     * model of the target request (Get movies).
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    @GET("discover/movie")
    fun getMovies(@Query("api_key") a_strApiKey : String, @Query("sort_by") a_strSortedBy : String) : Observable<Page>
}