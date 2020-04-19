package com.gabrielbmoro.programmingchallenge.core

import androidx.room.Room
import com.gabrielbmoro.programmingchallenge.domain.usecase.*
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepository
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepositoryImpl
import com.gabrielbmoro.programmingchallenge.repository.api.LoggedInterceptor
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseFactory
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val repositoryModule = module {
    single {
        ApiRepositoryImpl(
                Retrofit.Builder()
                        .baseUrl(ConfigVariables.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(
                                OkHttpClient.Builder()
                                        .connectTimeout(15, TimeUnit.SECONDS)
                                        .readTimeout(15, TimeUnit.SECONDS)
                                        .addInterceptor(LoggedInterceptor())
                                        .build()
                        )
                        .build()
                        .create(ApiRepository::class.java)
        )
    } bind MoviesRepository::class

    single {
        DataBaseRepositoryImpl(
                Room.databaseBuilder(
                        androidContext(),
                        DataBaseFactory::class.java,
                        ConfigVariables.DATABASE_NAME
                ).build().favoriteMoviesDAO()
        )
    } bind MoviesRepository::class
}

val usecaseModule = module {
    single { TopRatedMoviesUseCase(get<ApiRepositoryImpl>()) }
    single { PopularMoviesUseCase(get<ApiRepositoryImpl>()) }
    single { FavoriteMovieUseCase(get<DataBaseRepositoryImpl>()) }
    single { FavoriteMoviesUseCase(get<DataBaseRepositoryImpl>()) }
}