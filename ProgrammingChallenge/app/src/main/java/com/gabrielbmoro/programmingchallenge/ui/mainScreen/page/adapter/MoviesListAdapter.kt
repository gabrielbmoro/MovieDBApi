package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter

import androidx.annotation.DrawableRes
import com.gabrielbmoro.programmingchallenge.BuildConfig
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.model.Movie
import com.gabrielbmoro.programmingchallenge.ui.base.GeneralBaseAdapter
import kotlin.math.roundToInt

class MoviesListAdapter : GeneralBaseAdapter<MovieData>() {

    override fun layoutResourceId(): Int {
        return R.layout.view_holder_movie_card
    }

    fun setup(movieDataList: List<Movie>?) {
        movieDataList ?: return

        if (elements == null)
            elements = ArrayList()
        else
            elements?.clear()

        elements?.addAll(
                movieDataList.map { movie ->

                    val numberOfStars = (movie.votesAverage / AVERAGE_TOTAL) * STARS_AVAILABLE

                    MovieData(
                            posterPath = "${BuildConfig.baseImageAddress}${movie.posterPath}",
                            movieTitle = movie.title,
                            releaseDate = movie.releaseDate,
                            firstStar = gettingAccordingPosition(numberOfStars, 1),
                            secondStar = gettingAccordingPosition(numberOfStars, 2),
                            thirdStar = gettingAccordingPosition(numberOfStars, 3),
                            fourthStar = gettingAccordingPosition(numberOfStars, 4),
                            fifthStar = gettingAccordingPosition(numberOfStars, 5),
                            movieReference = movie
                    )
                }
        )
        notifyDataSetChanged()
    }

    @DrawableRes
    private fun gettingAccordingPosition(votes: Float, positionOrdinal: Int): Int {
        return when {
            votes > positionOrdinal -> R.drawable.ic_star
            votes < positionOrdinal -> {
                if (votes.roundToInt() == positionOrdinal)
                    R.drawable.ic_star_half
                else
                    R.drawable.ic_star_border
            }
            else -> R.drawable.ic_star_border
        }
    }

    companion object {
        const val AVERAGE_TOTAL = 10f
        const val STARS_AVAILABLE = 5f
    }

}