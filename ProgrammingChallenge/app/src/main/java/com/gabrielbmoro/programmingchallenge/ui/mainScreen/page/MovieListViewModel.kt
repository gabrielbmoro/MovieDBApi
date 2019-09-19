package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page

import android.app.Application
import com.gabrielbmoro.programmingchallenge.koin.api.ApiRepositoryImpl
import com.gabrielbmoro.programmingchallenge.model.MoviesListType
import com.gabrielbmoro.programmingchallenge.model.Page
import com.gabrielbmoro.programmingchallenge.ui.base.BaseViewModel
import com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter.MoviesListAdapter
import org.koin.core.inject
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

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

            val observable: Observable<Page>? = when (t) {
                MoviesListType.TOP_RATED_MOVIES -> {
                    api.getTopRatedMovies()
                }
                MoviesListType.POPULAR_RATED_MOVIES -> {
                    api.getPopularMovies()
                }

                MoviesListType.FAVORITE_MOVIES -> {
                    null
                }
            }

            observable?.let { response ->
                isLoading = true
                registerDisposable(
                        response.doAfterTerminate {
                            isLoading = false
                        }.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        {
                                            adapter.setup(it.results)
                                        },
                                        {

                                        }
                                )
                )
            }
        }
    }

    fun reload() {
        requestForMovies()
    }

}