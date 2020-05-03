package com.gabrielbmoro.programmingchallenge.presentation.viewModels

import com.gabrielbmoro.programmingchallenge.presentation.MainViewModel
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.Before

class MainViewModelTest {

    private lateinit var viewModel : MainViewModel

    @Before
    fun init(){
        viewModel = MainViewModel()
    }

    @Test
    fun whenThePopularPageIsSelected() {
        val given = MainViewModel.POPULAR_PAGE
        viewModel.setPage(given)
        assertThat(viewModel.getPage()).isEqualTo(MainViewModel.POPULAR_PAGE)
    }

    @Test
    fun whenTheTopRatedPageIsSelected() {
        val given = MainViewModel.TOP_RATED_PAGE
        viewModel.setPage(given)
        assertThat(viewModel.getPage()).isEqualTo(MainViewModel.TOP_RATED_PAGE)
    }

    @Test
    fun whenTheFavoritePageIsSelected() {
        val given = MainViewModel.FAVORITE_PAGE
        viewModel.setPage(given)
        assertThat(viewModel.getPage()).isEqualTo(MainViewModel.FAVORITE_PAGE)
    }

}