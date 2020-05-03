package com.gabrielbmoro.programmingchallenge.presentation.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult.Loading
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.PopularMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.TopRatedMoviesUseCase
import java.util.concurrent.locks.ReentrantLock

class MovieListViewModel(
        private val favoriteMoviesUseCase: FavoriteMoviesUseCase,
        private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
        private val popularMoviesUseCase: PopularMoviesUseCase
) : ViewModel() {

    private val moviesList = ArrayList<Movie>()
    private lateinit var type: MovieListType

    //region pagination
    private var currentPage = FIRST_PAGE
    var previousSize = moviesList.size
        private set
    val lock = ReentrantLock()
    //endregion

    fun setup(type: MovieListType): LiveData<ViewModelResult>? {
        return if (!lock.isLocked) {
            lock.lock()
            liveData {
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
                        lock.unlock()
                    }
                } catch (exception: Exception) {
                    Log.e("ERROR", exception.message ?: "--")
                    emit(ViewModelResult.Error)
                }
            }
        } else
            null
    }

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

    fun requestMore() = setup(type)

    companion object {
        const val FIRST_PAGE = 1
    }

}