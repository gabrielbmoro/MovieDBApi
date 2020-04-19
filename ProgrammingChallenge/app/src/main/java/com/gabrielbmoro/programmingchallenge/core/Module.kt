package com.gabrielbmoro.programmingchallenge.core

import androidx.room.Room
import com.gabrielbmoro.programmingchallenge.core.ConfigVariables.BASE_URL
import com.gabrielbmoro.programmingchallenge.core.ConfigVariables.DATABASE_NAME
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepository
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseFactory
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepositoryImpl
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseRepositoryImpl
import com.gabrielbmoro.programmingchallenge.presentation.movieList.MovieListViewModel
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMovieUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.PopularMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.TopRatedMoviesUseCase
import com.gabrielbmoro.programmingchallenge.presentation.detailedScreen.MovieDetailedViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataReceiverModule = module {
    single {
        ApiRepositoryImpl(
                Retrofit.Builder()
                        .baseUrl(BASE_URL)
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
        DataBaseRepositoryImpl(
                Room.databaseBuilder(
                        get(),
                        DataBaseFactory::class.java,
                        DATABASE_NAME
                ).build().favoriteMoviesDAO()
        )
    }
}

val useCaseModule = module {
    factory {
        FavoriteMovieUseCase(get<DataBaseRepositoryImpl>())
        TopRatedMoviesUseCase(get<ApiRepositoryImpl>())
        PopularMoviesUseCase(get<ApiRepositoryImpl>())
    }
}

val viewModelModule = module {
    viewModel {
        MovieListViewModel(
                application = androidApplication(),
                favoriteMoviesUseCase = get(),
                topRatedMoviesUseCase = get(),
                popularMoviesUseCase = get()
        )
        MovieDetailedViewModel(androidApplication(), favoriteMovieUseCase = get())
    }
}