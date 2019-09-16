package com.gabrielbmoro.programmingchallenge.ui.mainScreen.detailedScreen

import android.app.Application
import androidx.databinding.Bindable
import com.gabrielbmoro.programmingchallenge.BR
import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.model.Movie
import com.gabrielbmoro.programmingchallenge.ui.base.BaseViewModel

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

    fun setup(movie: Movie) {
        posterPath = "${BuildConfig.baseImageAddress}${movie.posterPath}"
        description = movie.overview
        title = movie.title
        language = movie.originalLanguage
        originalTitle = movie.originalTitle
        voteAverage = movie.votesAverage.toString()
        popularityAverage = movie.popularity.toString()
    }

    fun onFavoriteEvent() {

    }

}