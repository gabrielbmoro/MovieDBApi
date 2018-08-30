package com.gabrielbmoro.programmingchallenge.api

import com.gabrielbmoro.programmingchallenge.models.Page
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiServiceInterface {

    //?sort_by=vote_average.desc
    //?sort_by=popularity.desc
    @GET("discover/movie")
    fun getMovies(@Query("api_key") a_strApiKey : String, @Query("sort_by") a_strSortedBy : String) : Observable<Page>
}