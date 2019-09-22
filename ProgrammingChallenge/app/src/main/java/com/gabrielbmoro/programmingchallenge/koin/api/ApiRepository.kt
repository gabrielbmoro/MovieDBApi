package com.gabrielbmoro.programmingchallenge.koin.api

import com.gabrielbmoro.programmingchallenge.model.Page
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The inferface that defines the requests to API.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
interface ApiRepository {

    /**
     * This abstract method provides the implementation
     * model of the target request (Get movies).
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") a_strApiKey: String, @Query("sort_by") a_strSortedBy: String): Page
}