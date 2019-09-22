package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.gabrielbmoro.programmingchallenge.model.Movie
import com.gabrielbmoro.programmingchallenge.model.MoviesListType
import com.gabrielbmoro.programmingchallenge.model.MoviesRepository
import com.gabrielbmoro.programmingchallenge.ui.base.BaseViewModel
import com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter.MoviesListAdapter
import org.koin.core.inject

class MovieListViewModel(application: Application) : BaseViewModel(application) {

    val adapter = MoviesListAdapter()
    private val repository :  MoviesRepository by inject()
    private var type: MoviesListType? = null

    fun setup(listType: MoviesListType) {
        type = listType
        requestForMovies()
    }

    private fun requestForMovies() {
        type?.let { t ->

            isLoading = true

            val actionAfterRequest = { movies: List<Movie> ->
                adapter.setup(movies)
                isLoading = false
            }

            when (t) {
                MoviesListType.TOP_RATED_MOVIES ->
                    repository.getTopRatedMovies(viewModelScope, actionAfterRequest)
                MoviesListType.POPULAR_RATED_MOVIES ->
                    repository.getPopularMovies(viewModelScope, actionAfterRequest)
                MoviesListType.FAVORITE_MOVIES -> {
                    repository.getAllFavoriteMovies(viewModelScope, actionAfterRequest)
                }
            }
        }
    }

    fun reload() {
        requestForMovies()
    }

}