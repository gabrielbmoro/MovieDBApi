package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseRepositoryImpl
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

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
        val moviesRepository = mock(MoviesRepository::class.java)

        runBlocking {

            // when
            FavoriteMoviesUseCase(moviesRepository).execute()

            // then
            Mockito.verify(moviesRepository, times(1)).getFavoriteMovies()
        }
    }
}