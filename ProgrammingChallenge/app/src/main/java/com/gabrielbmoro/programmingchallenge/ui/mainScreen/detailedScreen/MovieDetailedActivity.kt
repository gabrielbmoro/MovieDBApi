package com.gabrielbmoro.programmingchallenge.ui.mainScreen.detailedScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.databinding.ActivityMovieDetailedBinding
import com.gabrielbmoro.programmingchallenge.models.Movie

class MovieDetailedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailedBinding
    private lateinit var viewModel: MovieDetailedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailedViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detailed)
        binding.viewModel = viewModel

        intent.getParcelableExtra<Movie>(MOVIE_INTENT_KEY)?.let{
            viewModel.setup(it)
            supportActionBar?.title = viewModel.title
        }
    }

    companion object {
        private const val MOVIE_INTENT_KEY = "movie key"
        fun startActivity(context: Context, movie: Movie) {
            context.startActivity(
                    Intent(context, MovieDetailedActivity::class.java).apply {
                        putExtra(MOVIE_INTENT_KEY, movie)
                    }
            )
        }
    }
}