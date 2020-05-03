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
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import com.gabrielbmoro.programmingchallenge.presentation.util.setImagePath
import com.gabrielbmoro.programmingchallenge.presentation.util.show
import kotlinx.android.synthetic.main.activity_movie_detailed.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailedActivity : AppCompatActivity(R.layout.activity_movie_detailed) {

    private val viewModel : MovieDetailedViewModel by viewModel()
    private val favoriteActionObserver = Observer<ViewModelResult> {
        viewModel.getMovie()?.isFavorite?.let {
            changeFavoriteViewsState(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (viewModel.getMovie()
                ?: intent.getParcelableExtra(MOVIE_INTENT_KEY) as? Movie)?.let { movie ->
            viewModel.setup(movie).observe(
                    this@MovieDetailedActivity,
                    favoriteActionObserver
            )
            setView(movie)
        } ?: finish()
    }

    private fun setView(movie: Movie) {
        supportActionBar?.title = movie.title
        backdrop.setImagePath(movie.posterPath)
        tvOriginalTitle.text = movie.originalTitle
        tvOverview.text = movie.overview
        tvLanguage.text = movie.originalLanguage
        tvPopularity.text = movie.popularity.toString()
        fiveStarsComponent.setVotesAvg(movie.votesAverage)
        changeFavoriteViewsState(movie.isFavorite)
        ivFavoriteOption?.setOnClickListener {
            viewModel.favoriteEvent(!movie.isFavorite)?.observe(
                    this@MovieDetailedActivity,
                    favoriteActionObserver
            )
        }
    }

    private fun changeFavoriteViewsState(isFavorite: Boolean) {
        ivFavoriteOption.show(true)
        ivFavoriteOption.setImageResource(
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