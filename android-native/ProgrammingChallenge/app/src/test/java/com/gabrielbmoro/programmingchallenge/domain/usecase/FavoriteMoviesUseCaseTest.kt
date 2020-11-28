package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseRepositoryImpl
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.koin.test.inject

class FavoriteMoviesUseCaseTest : KoinUnitTest() {

    @Test
    fun `favoriteMoviesUseCase using the correct repository`() {
        // given
        val favoriteUseCaseTest by inject<FavoriteMoviesUseCase>()

        // when
        val repository = favoriteUseCaseTest.repository

        // then
        assertThat(repository).isInstanceOf(DataBaseRepositoryImpl::class.java)
    }

    @Test
    fun `favoriteMoviesUseCase calling for the correct method`() {
        // given
        val moviesRepository = mockk<MoviesRepository>()
        every { moviesRepository.getFavoriteMovies() }.returns(null)

        // when
        FavoriteMoviesUseCase(moviesRepository).execute()

        // then
        verify(atLeast = 1) {
            moviesRepository.getFavoriteMovies()
        }
    }

}