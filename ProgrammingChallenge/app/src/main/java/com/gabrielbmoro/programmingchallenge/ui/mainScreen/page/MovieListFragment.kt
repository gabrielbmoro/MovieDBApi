package com.gabrielbmoro.programmingchallenge.ui.mainScreen.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.databinding.FragmentMoviesListBinding
import com.gabrielbmoro.programmingchallenge.model.MoviesListType
import com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.adapter.MoviesListAdapter

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, container, false)
        binding.viewModel = viewModel

        binding.swRefreshLayout.setOnRefreshListener {
            binding.swRefreshLayout.isRefreshing = false
            viewModel.reload()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(FRAGMENT_TYPE_KEY)?.let {
            mapAccordingToId(it)?.let { moviesListType ->
                when (moviesListType) {
                    MoviesListType.TOP_RATED_MOVIES, MoviesListType.POPULAR_RATED_MOVIES ->
                        binding.rvList.recycledViewPool.setMaxRecycledViews(MoviesListAdapter.VIEW_TYPE, 0)
                    else -> {
                    }
                }
                viewModel.setup(moviesListType)
            }
        }
    }

    private fun mapAccordingToId(id: Int): MoviesListType? {
        return when (id) {
            TOP_RATED_MOVIES_TYPE -> MoviesListType.TOP_RATED_MOVIES
            POPULAR_MOVIES_TYPE -> MoviesListType.POPULAR_RATED_MOVIES
            FAVORITE_MOVIES_TYPE -> MoviesListType.FAVORITE_MOVIES
            else -> null
        }
    }

    fun scrollToTop() {
        if (::binding.isInitialized) {
            binding.rvList.smoothScrollToPosition(0)
        }
    }

    companion object {
        private const val FRAGMENT_TYPE_KEY = "fragmentType"
        const val TOP_RATED_MOVIES_TYPE = -1
        const val POPULAR_MOVIES_TYPE = 0
        const val FAVORITE_MOVIES_TYPE = 1

        fun newInstance(fragmentType: Int): MovieListFragment {
            return MovieListFragment().apply {
                if (arrayOf(TOP_RATED_MOVIES_TYPE, POPULAR_MOVIES_TYPE, FAVORITE_MOVIES_TYPE).contains(fragmentType)) {
                    arguments = Bundle().apply {
                        putInt(FRAGMENT_TYPE_KEY, fragmentType)
                    }
                }
            }
        }

    }
}