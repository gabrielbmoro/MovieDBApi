package com.gabrielbmoro.programmingchallenge.presentation.movieList.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.presentation.FiveStarsComponent
import com.gabrielbmoro.programmingchallenge.presentation.detailedScreen.MovieDetailedActivity
import com.gabrielbmoro.programmingchallenge.presentation.util.setImagePath

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
                    MovieData(
                            posterPath = movie.posterPath,
                            movieTitle = movie.title,
                            releaseDate = movie.releaseDate,
                            votes = movie.votesAverage,
                            movieReference = movie
                    )
                }
        )
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val ivPoster: ImageView? = view.findViewById(R.id.ivPoster)
        private val tvTitle: TextView? = view.findViewById(R.id.tvTitle)
        private val tvReleaseDate: TextView? = view.findViewById(R.id.tvReleaseDate)
        private val fiveStarsComponent: FiveStarsComponent? = view.findViewById(R.id.fiveStarsComponent)

        fun bind(data: MovieData) {
            ivPoster?.setImagePath(data.posterPath)
            tvTitle?.text = data.movieTitle
            tvReleaseDate?.text = data.releaseDate
            fiveStarsComponent?.setVotesAvg(data.votes)
            view.setOnClickListener {
                (view.context as? Activity)?.let { activity ->
                    ivPoster?.let {
                        MovieDetailedActivity.startActivity(activity, data.movieReference, ivPoster)
                    }
                }
            }
        }
    }

    companion object {
        const val VIEW_TYPE = -123
    }
}