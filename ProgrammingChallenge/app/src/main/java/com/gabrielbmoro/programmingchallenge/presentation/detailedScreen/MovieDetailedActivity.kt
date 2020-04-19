package com.gabrielbmoro.programmingchallenge.presentation.detailedScreen

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detailed.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailedActivity : AppCompatActivity(R.layout.activity_movie_detailed) {

    private val viewModel: MovieDetailedViewModel by viewModel()
    private var favoriteMenu: MenuItem? = null

    override fun onStart() {
        super.onStart()

        (viewModel.getMovie() ?: intent.getParcelableExtra(MOVIE_INTENT_KEY) as? Movie)?.let { movie ->
            viewModel.setup(movie)
            setView(movie)
            viewModel.favoriteLiveData.observe(
                    this@MovieDetailedActivity,
                    Observer {
                        changeFavoriteViewsState(it)
                    }
            )
        } ?: finish()
    }

    private fun setView(movie: Movie) {
        supportActionBar?.title = movie.title
    }

    private fun changeFavoriteViewsState(it: Boolean) {
        if (it) {
            favoriteMenu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_heart_border)
            btnFavorite.setBackgroundColor(ContextCompat.getColor(this, R.color.favorite_button_off))
//            btnFavorite.setText(R.string.unfavorite)
        } else {
            favoriteMenu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_heart_filled)
            btnFavorite.setBackgroundColor(ContextCompat.getColor(this, R.color.favorite_button_onn))
//            btnFavorite.setText(R.string.favorite)
        }
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