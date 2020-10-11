package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.mockito.Mockito

class PopularMoviesUseCaseTest : KoinUnitTest() {

    @Test
    fun `popularMoviesUseCase using the correct repository`() {
        // given
        val popularUseCaseTest by inject<PopularMoviesUseCase>()

        // when
        val repository = popularUseCaseTest.repository

        // then
        Truth.assertThat(repository).isInstanceOf(ApiRepositoryImpl::class.java)
    }

    @Test
    fun `popularMoviesUseCase calling for the correct method`() {
        // given
        val repository = Mockito.mock(MoviesRepository::class.java)

        runBlocking {
            // when
            PopularMoviesUseCase(repository).execute(1)

            // then
            Mockito.verify(repository, Mockito.times(1)).getPopularMovies(1)
        }
    }

}