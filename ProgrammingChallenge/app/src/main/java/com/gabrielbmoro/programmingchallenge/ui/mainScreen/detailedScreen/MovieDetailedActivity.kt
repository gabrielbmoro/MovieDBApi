package com.gabrielbmoro.programmingchallenge.ui.mainScreen.detailedScreen

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.databinding.ActivityMovieDetailedBinding
import com.gabrielbmoro.programmingchallenge.model.Movie

class MovieDetailedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailedBinding
    private lateinit var viewModel: MovieDetailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailedViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detailed)
        binding.viewModel = viewModel

        intent.getParcelableExtra<Movie>(MOVIE_INTENT_KEY)?.let {
            viewModel.setup(it)
            supportActionBar?.title = viewModel.title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.favorite_menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.menu.favorite_menu_option -> viewModel.onFavoriteEvent()
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