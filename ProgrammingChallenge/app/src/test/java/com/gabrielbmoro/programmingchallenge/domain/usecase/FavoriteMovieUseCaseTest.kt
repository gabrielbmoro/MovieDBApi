package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.dataBase.DataBaseRepositoryImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import org.koin.test.inject

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieUseCaseTest : KoinUnitTest() {

    private val favoriteUseCaseTest by inject<FavoriteMovieUseCase>()

    @Test
    fun checkTheRepositoryInstanceType() {
        val given = favoriteUseCaseTest.repository
        assertThat(given).isInstanceOf(DataBaseRepositoryImpl::class.java)
    }
}