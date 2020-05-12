package com.gabrielbmoro.programmingchallenge.presentation.viewModels

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.PopularMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.TopRatedMoviesUseCase
import com.gabrielbmoro.programmingchallenge.presentation.movieList.MovieListViewModel
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.koin.test.inject

class MovieListViewModelTest : KoinUnitTest() {

    private lateinit var viewModel: MovieListViewModel
    private val favoriteMoviesUseCase by inject<FavoriteMoviesUseCase>()
    private val topRatedUseCase by inject<TopRatedMoviesUseCase>()
    private val popularMoviesUseCase by inject<PopularMoviesUseCase>()

    @Before
    fun init() {
        viewModel = MovieListViewModel(
                favoriteMoviesUseCase =  favoriteMoviesUseCase,
                topRatedMoviesUseCase = topRatedUseCase,
                popularMoviesUseCase = popularMoviesUseCase
        )
    }

    @Test
    fun `the list of movies can be reloaded`() {
        viewModel.setup(MovieListType.TopRated)

        viewModel.reload()

        assertThat(viewModel.currentPage).isEqualTo(MovieListViewModel.FIRST_PAGE)
        assertThat(viewModel.previousSize)
        assertThat(viewModel.type).isEqualTo(MovieListType.TopRated)
    }

}