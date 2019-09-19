package com.gabrielbmoro.programmingchallenge.koin

import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.koin.api.ApiRepository
import com.gabrielbmoro.programmingchallenge.koin.api.ApiRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        ApiRepositoryImpl(
                Retrofit.Builder()
                        .baseUrl(BuildConfig.baseUrl)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(
                                OkHttpClient.Builder()
                                        .connectTimeout(15, TimeUnit.SECONDS)
                                        .readTimeout(15, TimeUnit.SECONDS)
                                        .build()
                        )
                        .build()
                        .create(ApiRepository::class.java)
        )
    }
}