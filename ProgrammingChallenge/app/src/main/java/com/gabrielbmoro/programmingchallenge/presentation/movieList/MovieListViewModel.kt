package com.gabrielbmoro.programmingchallenge.presentation.movieList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult.Loading
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.PopularMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.TopRatedMoviesUseCase

class MovieListViewModel(
        application: Application,
        private val favoriteMoviesUseCase: FavoriteMoviesUseCase,
        private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
        private val popularMoviesUseCase: PopularMoviesUseCase
) : AndroidViewModel(application) {

    private lateinit var type : MovieListType

    fun setup(type:MovieListType): LiveData<ViewModelResult> {
        return liveData {
            emit(Loading)
            try {
                val movies = when(type){
                    MovieListType.TopRated  -> topRatedMoviesUseCase.execute()
                    MovieListType.Popular -> popularMoviesUseCase.execute()
                    MovieListType.Favorite -> favoriteMoviesUseCase.execute()
                }
                this@MovieListViewModel.type = type
                emit(ViewModelResult.Success(movies))
            } catch (exception: Exception) {
                emit(ViewModelResult.Error(exception))
            }
        }
    }

    fun reload(): LiveData<ViewModelResult>? {
        return if(::type.isInitialized){
            setup(type)
        } else null
    }
}