package com.gabrielbmoro.programmingchallenge.dependenciesInjector

import com.gabrielbmoro.programmingchallenge.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {

    single {
        RepositoryImpl(
                retrofit2.Retrofit.Builder()
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