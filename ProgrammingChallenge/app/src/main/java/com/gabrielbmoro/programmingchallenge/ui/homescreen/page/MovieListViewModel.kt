package com.gabrielbmoro.programmingchallenge.ui.homescreen.page

import android.app.Application
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess.Companion.BASE_URL
import com.gabrielbmoro.programmingchallenge.models.MoviesListType
import com.gabrielbmoro.programmingchallenge.ui.base.BaseViewModel
import com.gabrielbmoro.programmingchallenge.ui.homescreen.page.adapter.MovieData
import com.gabrielbmoro.programmingchallenge.ui.homescreen.page.adapter.MoviesListAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MovieListViewModel(application: Application) : BaseViewModel(application) {

    val adapter = MoviesListAdapter()
    private val api = ApiServiceAccess()

    fun setup(listType: MoviesListType) {
        when (listType) {
            MoviesListType.TOP_RATED_MOVIES, MoviesListType.POPULAR_RATED_MOVIES -> {
                api.getMovies(listType.requestParameter).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    adapter.setup(
                                            it.results?.map { movie ->
                                                MovieData(
                                                        posterPath = "https://image.tmdb.org/t/p/w780${movie.posterPath}",
                                                        movieTitle = movie.title
                                                )
                                            }
                                    )
                                },
                                {

                                }
                        )
            }
            MoviesListType.FAVORITE_MOVIES -> {

            }
        }

    }

}