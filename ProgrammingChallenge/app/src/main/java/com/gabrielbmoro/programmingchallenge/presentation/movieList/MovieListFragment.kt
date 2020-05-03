package com.gabrielbmoro.programmingchallenge.presentation.movieList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.domain.model.convertToMovieListType
import com.gabrielbmoro.programmingchallenge.presentation.util.show
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movies_list) {

    private val viewModel: MovieListViewModel by viewModel()

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
                rvList.adapterImplementation()?.setup(viewModel.movies())
                swRefreshLayout.show(true)
                progressBar.show(false)
                tvError.show(false)
            }
            is ViewModelResult.Updated -> {
                rvList.adapterImplementation()?.update(viewModel.newPart())
                swRefreshLayout.show(true)
                progressBar.show(false)
                tvError.show(false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getInt(MOVIE_TYPE_VALUE) != null) {
            arguments?.getInt(MOVIE_TYPE_VALUE)?.convertToMovieListType()?.let { type ->
                setupViewModel(type)
                setupRecyclerView(type)
            }
        }
    }

    private fun setupViewModel(type: MovieListType) {
        viewModel.setup(type)?.observe(
                viewLifecycleOwner, observer
        )
    }

    private fun setupRecyclerView(type: MovieListType) {
        if (type == MovieListType.TopRated || type == MovieListType.Popular) {
            rvList.paginationSupport {
                viewModel.requestMore()?.observe(
                        viewLifecycleOwner,
                        observer
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
        rvList.scrollToTop()
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