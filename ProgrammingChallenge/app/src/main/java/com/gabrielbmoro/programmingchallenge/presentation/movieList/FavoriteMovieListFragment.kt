package com.gabrielbmoro.programmingchallenge.presentation.movieList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.presentation.favoriteMovieList.FavoriteMoviesListAdapter
import com.gabrielbmoro.programmingchallenge.presentation.favoriteMovieList.FavoriteMoviesViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movies_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieListFragment : Fragment(R.layout.fragment_favorite_movies_list) {

    private val viewModel: FavoriteMoviesViewModel by viewModel()
    private val adapter = FavoriteMoviesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_favorite_movies_list_rv_list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.setup()?.observe(
                viewLifecycleOwner,
                Observer {
                    adapter.submitList(it)
                }
        )
    }

}