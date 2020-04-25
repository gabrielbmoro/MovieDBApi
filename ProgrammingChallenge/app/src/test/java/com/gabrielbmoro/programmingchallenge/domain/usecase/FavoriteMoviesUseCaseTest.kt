package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseRepositoryImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

@RunWith(MockitoJUnitRunner::class)
class FavoriteMoviesUseCaseTest : KoinUnitTest() {

    @Test
    fun checkTheRepositoryInstanceType() {
        val favoriteUseCaseTest by inject<FavoriteMoviesUseCase>()
        val given = favoriteUseCaseTest.repository
        assertThat(given).isInstanceOf(DataBaseRepositoryImpl::class.java)
    }

    @Test
    fun checkTheRepositoryInstanceIsCallingForTheCorrectMethod() {
        val repository = mock(MoviesRepository::class.java)
        val favoriteUseCaseTest = FavoriteMoviesUseCase(repository)

        runBlocking {
            favoriteUseCaseTest.execute()
            Mockito.verify(repository, times(1)).getFavoriteMovies()
        }
    }
}