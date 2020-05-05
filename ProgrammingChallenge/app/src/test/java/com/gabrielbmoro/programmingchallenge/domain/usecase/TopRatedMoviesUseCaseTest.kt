package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import org.mockito.Mockito

class TopRatedMoviesUseCaseTest : KoinUnitTest() {

    @Test
    fun `topRatedUseCase using for the correct repository`() {
        // given
        val topRatedUseCaseTest by inject<TopRatedMoviesUseCase>()

        // when
        val given = topRatedUseCaseTest.repository

        // then
        Truth.assertThat(given).isInstanceOf(ApiRepositoryImpl::class.java)
    }

    @Test
    fun `topRatedUseCase calling for the correct method`() {
        // given
        val repository = Mockito.mock(MoviesRepository::class.java)

        runBlocking {
            // when
            TopRatedMoviesUseCase(repository).execute(1)

            // then
            Mockito.verify(repository, Mockito.times(1)).getTopRatedMovies(1)
        }
    }
}