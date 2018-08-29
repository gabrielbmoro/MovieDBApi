package com.gabrielbmoro.programmingchallenge.api

import com.gabrielbmoro.programmingchallenge.Constants
import com.gabrielbmoro.programmingchallenge.models.Page
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    //?sort_by=vote_average.desc
    //?sort_by=popularity.desc
    @GET("discover/movie")
    fun getMovies(@Query("api_key") a_strApiKey : String, @Query("sort_by") a_strSortedBy : String) : Call<Page>

    companion object Factory {
        fun create() : ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()
            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}