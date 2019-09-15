package com.gabrielbmoro.programmingchallenge.api

import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.models.Page
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * This class creates the api service.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class ApiServiceAccess {

    private val service: ApiServiceInterface

    /**
     * The constructor method creates the retrofit object.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    init {
        val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .build())
                .build()
        service = retrofit.create(ApiServiceInterface::class.java)
    }

    /**
     * Return the movies using the retrofit library.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun getMovies(a_strSortedBy: String): Observable<Page> {
        return service.getMovies(BuildConfig.token, a_strSortedBy)
    }
}
