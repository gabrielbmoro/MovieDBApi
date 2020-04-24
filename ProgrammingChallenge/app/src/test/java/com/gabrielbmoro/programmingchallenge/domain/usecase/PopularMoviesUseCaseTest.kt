package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.repository.api.ApiRepositoryImpl
import com.google.common.truth.Truth
import org.junit.Test
import org.koin.test.inject

class PopularMoviesUseCaseTest: KoinUnitTest() {

    private val popularUseCaseTest by inject<PopularMoviesUseCase>()

    @Test
    fun checkTheRepositoryInstanceType() {
        val given = popularUseCaseTest.repository
        Truth.assertThat(given).isInstanceOf(ApiRepositoryImpl::class.java)
    }
}