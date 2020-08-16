package com.gabrielbmoro.programmingchallenge.presentation.components.adapter

import android.app.Activity
import android.view.View
import com.gabrielbmoro.programmingchallenge.presentation.util.setImagePath
import androidx.recyclerview.widget.RecyclerView
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.presentation.detailedScreen.MovieDetailedActivity
import kotlinx.android.synthetic.main.view_holder_movie_card.view.*

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie) {
        view.view_holder_movie_card_poster.setImagePath(movie.posterPath)
        view.view_holder_movie_card_title.text = movie.title
        view.view_holder_movie_card_release_date.text = movie.releaseDate
        view.view_holder_movie_card_five_stars_component.setVotesAvg(movie.votesAverage)
        view.setOnClickListener {
            (view.context as? Activity)?.let { activity ->
                view.view_holder_movie_card_poster?.let {
                    MovieDetailedActivity.startActivity(activity, movie, view.view_holder_movie_card_poster)
                }
            }
        }
    }
}