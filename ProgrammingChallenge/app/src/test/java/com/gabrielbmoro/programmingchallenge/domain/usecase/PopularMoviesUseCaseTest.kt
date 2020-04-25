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
    fun checkTheRepositoryInstanceType() {
        val popularUseCaseTest by inject<PopularMoviesUseCase>()
        val given = popularUseCaseTest.repository
        Truth.assertThat(given).isInstanceOf(ApiRepositoryImpl::class.java)
    }

    @Test
    fun checkTheRepositoryInstanceIsCallingForTheCorrectMethod() {
        val repository = Mockito.mock(MoviesRepository::class.java)
        val popularUseCaseTest = PopularMoviesUseCase(repository)

        runBlocking {
            popularUseCaseTest.execute(1)
            Mockito.verify(repository, Mockito.times(1)).getPopularMovies(1)
        }
    }

    @Test
    fun checkTheRepositoryInstanceIsCallingForTheInCorrectMethod() {
        val repository = Mockito.mock(MoviesRepository::class.java)
        val popularUseCaseTest = PopularMoviesUseCase(repository)

        runBlocking {
            popularUseCaseTest.execute(1)
            Mockito.verify(repository, Mockito.times(0)).getTopRatedMovies(1)
        }
    }
}