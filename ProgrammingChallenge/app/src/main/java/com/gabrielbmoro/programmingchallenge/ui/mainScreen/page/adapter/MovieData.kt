package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter

import android.view.View
import androidx.annotation.DrawableRes
import com.gabrielbmoro.programmingchallenge.model.Movie
import com.gabrielbmoro.programmingchallenge.ui.mainScreen.detailedScreen.MovieDetailedActivity

data class MovieData(
        val posterPath: String,
        val movieTitle: String,
        val releaseDate: String,
        @DrawableRes
        val firstStar: Int,
        @DrawableRes
        val secondStar: Int,
        @DrawableRes
        val thirdStar: Int,
        @DrawableRes
        val fourthStar: Int,
        @DrawableRes
        val fifthStar: Int,
        var movieReference: Movie
) {
    fun onClick(v: View) {
        MovieDetailedActivity.startActivity(v.context, movieReference)
    }
}