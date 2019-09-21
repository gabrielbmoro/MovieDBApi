package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.gabrielbmoro.programmingchallenge.R
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
        val activity = v.context as? Activity
        val imageView = v.findViewById<ImageView>(R.id.ivPoster)
        if (activity != null && imageView != null) {
            MovieDetailedActivity.startActivity(activity, movieReference, imageView)
        }
    }
}