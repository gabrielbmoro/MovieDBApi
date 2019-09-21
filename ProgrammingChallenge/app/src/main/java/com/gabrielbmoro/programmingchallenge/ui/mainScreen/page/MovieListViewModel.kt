package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.gabrielbmoro.programmingchallenge.koin.api.ApiRepositoryImpl
import com.gabrielbmoro.programmingchallenge.model.Movie
import com.gabrielbmoro.programmingchallenge.model.MoviesListType
import com.gabrielbmoro.programmingchallenge.ui.base.BaseViewModel
import com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter.MoviesListAdapter
import org.koin.core.inject

class MovieListViewModel(application: Application) : BaseViewModel(application) {

    val adapter = MoviesListAdapter()
    private val api: ApiRepositoryImpl by inject()
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
                    api.getTopRatedMovies(viewModelScope, actionAfterRequest)
                MoviesListType.POPULAR_RATED_MOVIES ->
                    api.getPopularMovies(viewModelScope, actionAfterRequest)
                MoviesListType.FAVORITE_MOVIES -> {
                    isLoading = false
                }
            }
        }
    }

    fun reload() {
        requestForMovies()
    }

}