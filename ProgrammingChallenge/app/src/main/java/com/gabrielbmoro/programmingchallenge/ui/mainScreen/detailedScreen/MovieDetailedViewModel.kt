package com.gabrielbmoro.programmingchallenge.ui.mainScreen.detailedScreen

import android.app.Application
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.gabrielbmoro.programmingchallenge.BR
import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.model.Movie
import com.gabrielbmoro.programmingchallenge.model.MoviesRepository
import com.gabrielbmoro.programmingchallenge.ui.base.BaseViewModel
import org.koin.core.inject

class MovieDetailedViewModel(application: Application) : BaseViewModel(application) {

    @get:Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @get:Bindable
    var posterPath: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.posterPath)
        }

    @get:Bindable
    var language: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.language)
        }

    @get:Bindable
    var originalTitle: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.originalTitle)
        }

    @get:Bindable
    var voteAverage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.voteAverage)
        }

    @get:Bindable
    var popularityAverage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.popularityAverage)
        }

    private var baseMovie: Movie? = null

    private val repository: MoviesRepository by inject()

    fun setup(movie: Movie) {
        baseMovie = movie
        baseMovie?.let {
            posterPath = "${BuildConfig.baseImageAddress}${it.posterPath}"
            description = it.overview
            title = it.title
            language = it.originalLanguage
            originalTitle = it.originalTitle
            voteAverage = it.votesAverage.toString()
            popularityAverage = it.popularity.toString()
        }
    }

    fun onFavoriteEvent() {
        baseMovie?.let {
            if(!it.isFavorite) {
                repository.saveMovieAsFavorite(
                        viewModelScope,
                        it
                ) {
                    Toast.makeText(getApplication(), "favorite", Toast.LENGTH_SHORT).show()
                }
            } else {
                repository.unFavorite(
                        viewModelScope,
                        it
                ) {
                    Toast.makeText(getApplication(), "unfavorite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}