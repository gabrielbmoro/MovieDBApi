package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepositoryImpl
import com.google.common.truth.Truth
import org.junit.Test
import org.koin.test.inject

class TopRatedMoviesUseCaseTest: KoinUnitTest() {

    private val topRatedUseCaseTest by inject<TopRatedMoviesUseCase>()

    @Test
    fun checkTheRepositoryInstanceType() {
        val given = topRatedUseCaseTest.repository
        Truth.assertThat(given).isInstanceOf(ApiRepositoryImpl::class.java)
    }
}