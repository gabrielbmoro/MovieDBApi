package com.gabrielbmoro.programmingchallenge.presentation.movieList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.domain.model.convertToMovieListType
import com.gabrielbmoro.programmingchallenge.presentation.movieList.adapter.MoviesListAdapter
import com.gabrielbmoro.programmingchallenge.presentation.util.show
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MovieListFragment : Fragment(R.layout.fragment_movies_list) {

    private lateinit var viewModel : MovieListViewModel

    private val adapter = MoviesListAdapter()

    private val observer = Observer<ViewModelResult> { result ->
        swRefreshLayout.isRefreshing = false
        when (result) {
            is ViewModelResult.Error -> {
                tvError.show(true)
                progressBar.show(false)
                swRefreshLayout.show(false)
            }
            is ViewModelResult.Loading -> {
                progressBar.show(true)
                tvError.show(false)
                swRefreshLayout.show(false)
            }
            is ViewModelResult.Success -> {
                adapter.setup(viewModel.movies())
                swRefreshLayout.show(true)
                progressBar.show(false)
                tvError.show(false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        rvList.adapter = adapter
        arguments?.getInt(MOVIE_TYPE_VALUE)?.let {
            it.convertToMovieListType()?.let { type ->
                viewModel.setup(type).observe(
                        viewLifecycleOwner, observer
                )
            }
        }
        swRefreshLayout.setOnRefreshListener {
            viewModel.reload()?.observe(
                    viewLifecycleOwner, observer
            )
        }
    }

    fun scrollToTop() {
        rvList.scrollToPosition(0)
    }

    companion object {

        private const val MOVIE_TYPE_VALUE = "aspd"

        fun newInstance(type: MovieListType): MovieListFragment {
            return MovieListFragment().apply {
                arguments = Bundle().also { bundle ->
                    bundle.putInt(MOVIE_TYPE_VALUE, type.value)
                }
            }
        }

    }
}