package com.gabrielbmoro.programmingchallenge.presentation.movieList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.presentation.ViewModelResult
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.domain.model.convertToMovieListType
import com.gabrielbmoro.programmingchallenge.presentation.favoriteMovieList.ScrollableFragment
import com.gabrielbmoro.programmingchallenge.presentation.util.show
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(R.layout.fragment_movies_list), ScrollableFragment {

    private val viewModel: MovieListViewModel by viewModel()

    private val observer = Observer<ViewModelResult> { result ->
        fragment_movies_list_sw_refresh.isRefreshing = false
        when (result) {
            is ViewModelResult.Error -> {
                fragment_movies_list_tv_error.show(true)
                fragment_movies_list_progress_bar.stop()
                fragment_movies_list_sw_refresh.show(false)
            }
            is ViewModelResult.Loading -> {
                fragment_movies_list_progress_bar.start()
                fragment_movies_list_tv_error.show(false)
                fragment_movies_list_sw_refresh.show(false)
            }
            is ViewModelResult.Success -> {
                fragment_movies_list_rv_list.adapterImplementation()?.setup(viewModel.movies())
                showTheRefreshLayout()
            }
            is ViewModelResult.Updated -> {
                fragment_movies_list_rv_list.adapterImplementation()?.update(viewModel.newPart())
                showTheRefreshLayout()
            }
        }
    }

    private fun showTheRefreshLayout() {
        fragment_movies_list_sw_refresh.show(true)
        fragment_movies_list_progress_bar.stop()
        fragment_movies_list_tv_error.show(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieTypeFromIntent()?.let { type ->
            setupViewModel(type)
            setupRecyclerView(type)
        }
    }

    private fun getMovieTypeFromIntent(): MovieListType? {
        return arguments?.getInt(MOVIE_TYPE_VALUE)?.convertToMovieListType()
    }

    private fun setupViewModel(type: MovieListType) {
        viewModel.onMoviesListReceived.observe(viewLifecycleOwner, observer)
        viewModel.setup(type)
    }

    private fun setupRecyclerView(type: MovieListType) {
        if (type == MovieListType.TopRated || type == MovieListType.Popular) {
            fragment_movies_list_rv_list.paginationSupport {
                viewModel.requestMore()
            }
        }
        fragment_movies_list_sw_refresh.setOnRefreshListener {
            viewModel.reload()
        }
    }

    override fun scrollToTop() {
        fragment_movies_list_rv_list.scrollToTop()
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