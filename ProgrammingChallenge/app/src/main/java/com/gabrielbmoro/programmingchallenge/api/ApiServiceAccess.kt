package com.gabrielbmoro.programmingchallenge.api

import com.gabrielbmoro.programmingchallenge.models.Page
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * This class creates the api service.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class ApiServiceAccess {

    val service : ApiServiceInterface

    /**
     * The companion object will be used like a kind of singleton
     */
    companion object {
        val BASE_URL : String = "https://api.themoviedb.org/3/"
        val API_KEY  : String = "755e0c67ac2fa886e775fb9057f0a32f"
    }

    /**
     * The constructor method creates the retrofit object.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    init {
        val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
        service = retrofit.create(ApiServiceInterface::class.java)
    }

    /**
     * Return the movies using the retrofit library.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun getMovies(a_strSortedBy : String) : Observable<Page> {
        return service.getMovies(API_KEY, a_strSortedBy)
    }
}