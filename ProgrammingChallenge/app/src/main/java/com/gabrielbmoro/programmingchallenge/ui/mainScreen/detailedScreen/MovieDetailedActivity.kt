package com.gabrielbmoro.programmingchallenge.ui.mainScreen.detailedScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.databinding.ActivityMovieDetailedBinding
import com.gabrielbmoro.programmingchallenge.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detailed.*

class MovieDetailedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailedBinding
    private lateinit var viewModel: MovieDetailedViewModel
    private var favoriteMenu: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailedViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detailed)
        binding.viewModel = viewModel

        intent.getParcelableExtra<Movie>(MOVIE_INTENT_KEY)?.let {
            viewModel.setup(it)
            changeFavoriteViewsState(it.isFavorite)
            supportActionBar?.title = viewModel.title
        }

        viewModel.favoriteLiveData.observe(
                this,
                Observer {
                    changeFavoriteViewsState(it)
                }
        )

    }

    private fun changeFavoriteViewsState(it: Boolean) {
        if (it) {
            favoriteMenu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_heart_border)
            btnFavorite.setBackgroundColor(ContextCompat.getColor(this, R.color.favorite_button_off))
            btnFavorite.setText(R.string.unfavorite)
        } else {
            favoriteMenu?.icon = ContextCompat.getDrawable(this, R.drawable.ic_heart_filled)
            btnFavorite.setBackgroundColor(ContextCompat.getColor(this, R.color.favorite_button_onn))
            btnFavorite.setText(R.string.favorite)
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