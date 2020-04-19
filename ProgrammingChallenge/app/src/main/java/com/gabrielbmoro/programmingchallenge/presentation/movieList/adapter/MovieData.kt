package com.gabrielbmoro.programmingchallenge.presentation.movieList.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.presentation.detailedScreen.MovieDetailedActivity

data class MovieData(
        val posterPath: String,
        val movieTitle: String,
        val releaseDate: String,
        val votes : Float,
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