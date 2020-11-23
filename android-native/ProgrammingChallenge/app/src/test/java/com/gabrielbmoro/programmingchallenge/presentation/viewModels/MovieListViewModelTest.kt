package com.gabrielbmoro.programmingchallenge.presentation.viewModels

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.domain.usecase.PopularMoviesUseCase
import com.gabrielbmoro.programmingchallenge.domain.usecase.TopRatedMoviesUseCase
import com.gabrielbmoro.programmingchallenge.presentation.movieList.MovieListViewModel
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.koin.test.inject

class MovieListViewModelTest : KoinUnitTest() {

    private val topRatedUseCase by inject<TopRatedMoviesUseCase>()
    private val popularMoviesUseCase by inject<PopularMoviesUseCase>()

    @Test
    fun `the list of movies can be reloaded`() {
        val viewModel = MovieListViewModel(
                topRatedMoviesUseCase = topRatedUseCase,
                popularMoviesUseCase = popularMoviesUseCase
        )
        viewModel.setup(MovieListType.TopRated)

        viewModel.reload()

        assertThat(viewModel.currentPage).isEqualTo(MovieListViewModel.FIRST_PAGE)
        assertThat(viewModel.previousSize)
        assertThat(viewModel.type).isEqualTo(MovieListType.TopRated)
    }

}