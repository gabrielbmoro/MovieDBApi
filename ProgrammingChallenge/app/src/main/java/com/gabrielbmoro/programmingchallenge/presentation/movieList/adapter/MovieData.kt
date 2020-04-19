package com.gabrielbmoro.programmingchallenge.presentation.movieList.adapter

import com.gabrielbmoro.programmingchallenge.domain.model.Movie

data class MovieData(
        val posterPath: String,
        val movieTitle: String,
        val releaseDate: String,
        val votes : Float,
        var movieReference: Movie
)