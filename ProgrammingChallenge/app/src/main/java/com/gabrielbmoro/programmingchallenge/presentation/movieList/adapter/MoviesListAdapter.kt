package com.gabrielbmoro.programmingchallenge.presentation.movieList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.core.ConfigVariables.BASE_IMAGE_ADDRESS
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import kotlin.math.roundToInt

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    private val elements = ArrayList<MovieData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_card, parent, false))
    }

    override fun getItemCount() = elements.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        try {
            elements[position]
        } catch (indexOutOfBounds: IndexOutOfBoundsException) {
            null
        }?.let { data ->
            holder.bind(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    fun setup(movieDataList: List<Movie>) {
        elements.clear()
        elements.addAll(
                movieDataList.map { movie ->

                    val numberOfStars = (movie.votesAverage / AVERAGE_TOTAL) * STARS_AVAILABLE

                    MovieData(
                            posterPath = "${BASE_IMAGE_ADDRESS}${movie.posterPath}",
                            movieTitle = movie.title,
                            releaseDate = movie.releaseDate.toString(),
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
            votes >= positionOrdinal -> R.drawable.ic_star
            votes < positionOrdinal -> {
                if (votes.roundToInt() == positionOrdinal)
                    R.drawable.ic_star_half
                else
                    R.drawable.ic_star_border
            }
            else -> R.drawable.ic_star_border
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: MovieData) {

        }
    }

    companion object {
        const val AVERAGE_TOTAL = 10f
        const val STARS_AVAILABLE = 5f
        const val VIEW_TYPE = -123
    }
}