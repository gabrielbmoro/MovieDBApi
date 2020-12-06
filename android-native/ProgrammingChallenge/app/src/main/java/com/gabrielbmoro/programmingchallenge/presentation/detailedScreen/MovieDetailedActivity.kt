package com.gabrielbmoro.programmingchallenge.presentation.detailedScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.presentation.util.setImagePath
import com.gabrielbmoro.programmingchallenge.presentation.util.show
import kotlinx.android.synthetic.main.activity_movie_detailed.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.IllegalArgumentException

class MovieDetailedActivity : AppCompatActivity(R.layout.activity_movie_detailed) {

    private val viewModel: MovieDetailedViewModel by viewModel {
        parametersOf(
                intent.getParcelableExtra(MOVIE_INTENT_KEY) as? Movie
                        ?: throw IllegalArgumentException(
                                "${MovieDetailedActivity::class.java.simpleName} requires arg $MOVIE_INTENT_KEY"
                        )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView(viewModel.movie)

        viewModel.onFavoriteMovieEvent.observe(
                this@MovieDetailedActivity,
                Observer {
                    changeFavoriteViewsState(viewModel.movie.isFavorite)
                }
        )
    }

    private fun setView(movie: Movie) {
        supportActionBar?.title = movie.title
        activity_movie_detailed_backdrop.setImagePath(movie.posterPath)
        activity_movie_detailed_tv_original_title.text = movie.originalTitle
        activity_movie_detailed_overview.text = movie.overview
        activity_movie_detailed_language_title.text = movie.originalLanguage
        activity_movie_detailed_tv_popularity.text = movie.popularity.toString()
        activity_movie_detailed_five_stars_component.setVotesAvg(movie.votesAverage)
        changeFavoriteViewsState(movie.isFavorite)
        activity_movie_detailed_favorite_icon?.setOnClickListener {
            viewModel.isToFavoriteOrUnFavorite(!movie.isFavorite)
        }
    }

    private fun changeFavoriteViewsState(isFavorite: Boolean) {
        activity_movie_detailed_favorite_icon.show(true)
        activity_movie_detailed_favorite_icon.setImageResource(
                if (isFavorite) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_border
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private const val MOVIE_INTENT_KEY = "movie key"

        /**
         * About animation
         * Reference: https://guides.codepath.com/android/shared-element-activity-transition
         */
        fun startActivity(context: Activity, movie: Movie, ivImageShared: View) {
            context.startActivity(
                    Intent(context, MovieDetailedActivity::class.java).apply {
                        putExtra(MOVIE_INTENT_KEY, movie)
                    },
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context,
                            ivImageShared,
                            context.resources.getString(R.string.transition_name)
                    ).toBundle()
            )
        }
    }
}