package com.gabrielbmoro.programmingchallenge.presentation.movieList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult.Loading
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.PopularMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.TopRatedMoviesUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieListViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val favoriteMoviesUseCase: FavoriteMoviesUseCase by inject()
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase by inject()
    private val popularMoviesUseCase: PopularMoviesUseCase by inject()
    private val moviesList = ArrayList<Movie>()
    private lateinit var type: MovieListType

    //region pagination
    private var currentPage = FIRST_PAGE
    var previousSize = moviesList.size
        private set
    //endregion

    fun setup(type: MovieListType): LiveData<ViewModelResult> {
        return liveData {
            emit(Loading)
            try {
                val movies = when (type) {
                    MovieListType.TopRated, MovieListType.Popular -> requestMovies(type)
                    MovieListType.Favorite -> favoriteMoviesUseCase.execute()
                }
                this@MovieListViewModel.type = type
                movies?.let {
                    moviesList.addAll(it)
                    if (previousSize == 0) {
                        emit(ViewModelResult.Success)
                    } else {
                        emit(ViewModelResult.Updated)
                    }
                }
            } catch (exception: Exception) {
                Log.e("ERROR", exception.message ?: "--")
                emit(ViewModelResult.Error)
            }
        }
    }

    fun isPaginated() = ::type.isInitialized && (type == MovieListType.Popular || type == MovieListType.TopRated)

    private suspend fun requestMovies(type: MovieListType): List<Movie>? {
        return when (type) {
            MovieListType.TopRated -> {
                topRatedMoviesUseCase.execute(currentPage)
            }
            MovieListType.Popular -> {
                popularMoviesUseCase.execute(currentPage)
            }
            else -> null
        }?.let { page ->
            if (page.hasMorePages)
                currentPage++
            previousSize = moviesList.size
            page.movies
        }
    }

    fun reload(): LiveData<ViewModelResult>? {
        return if (::type.isInitialized) {
            currentPage = FIRST_PAGE
            moviesList.clear()
            setup(type)
        } else null
    }

    fun movies() = moviesList.toList()

    fun newPart() = moviesList.subList(previousSize, moviesList.lastIndex).toList()

    @Synchronized
    fun requestMore() = setup(type)

    companion object {
        const val FIRST_PAGE = 1
    }

}