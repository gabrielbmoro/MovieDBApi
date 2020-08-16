package com.gabrielbmoro.programmingchallenge.presentation.components.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.gabrielbmoro.programmingchallenge.presentation.util.setImagePath
import androidx.recyclerview.widget.RecyclerView
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.presentation.components.FiveStarsComponent
import com.gabrielbmoro.programmingchallenge.presentation.detailedScreen.MovieDetailedActivity

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val ivPoster: ImageView? = view.findViewById(R.id.ivPoster)
    private val tvTitle: TextView? = view.findViewById(R.id.tvTitle)
    private val tvReleaseDate: TextView? = view.findViewById(R.id.tvReleaseDate)
    private val fiveStarsComponent: FiveStarsComponent? = view.findViewById(R.id.fiveStarsComponent)

    fun bind(movie: Movie) {
        ivPoster?.setImagePath(movie.posterPath)
        tvTitle?.text = movie.title
        tvReleaseDate?.text = movie.releaseDate
        fiveStarsComponent?.setVotesAvg(movie.votesAverage)
        view.setOnClickListener {
            (view.context as? Activity)?.let { activity ->
                ivPoster?.let {
                    MovieDetailedActivity.startActivity(activity, movie, ivPoster)
                }
            }
        }
    }
}