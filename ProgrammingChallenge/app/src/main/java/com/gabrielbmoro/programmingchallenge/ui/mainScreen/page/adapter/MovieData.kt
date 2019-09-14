package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter

import androidx.annotation.DrawableRes

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
        val fifthStar: Int
)