package com.gabrielbmoro.programmingchallenge.koin

import androidx.room.Room
import com.gabrielbmoro.programmingchallenge.koin.ConfigVariables.BASE_URL
import com.gabrielbmoro.programmingchallenge.koin.ConfigVariables.DATABASE_NAME
import com.gabrielbmoro.programmingchallenge.koin.api.ApiRepository
import com.gabrielbmoro.programmingchallenge.koin.dataBase.DataBaseFactory
import com.gabrielbmoro.programmingchallenge.model.MoviesRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single {
        MoviesRepository(
                service = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(
                                OkHttpClient.Builder()
                                        .connectTimeout(15, TimeUnit.SECONDS)
                                        .readTimeout(15, TimeUnit.SECONDS)
                                        .build()
                        )
                        .build()
                        .create(ApiRepository::class.java),
                favoriteDAO = Room.databaseBuilder(
                        get(),
                        DataBaseFactory::class.java,
                        DATABASE_NAME
                ).build().favoriteMoviesDAO()
        )
    }
}