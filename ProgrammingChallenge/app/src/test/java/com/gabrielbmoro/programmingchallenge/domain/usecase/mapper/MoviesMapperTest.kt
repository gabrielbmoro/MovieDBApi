package com.gabrielbmoro.programmingchallenge.domain.usecase.mapper

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MoviesMapperTest {

    @Test
    fun mapper_nullResponse_ReturnsEmptyList() {
        assertThat(MoviesMapper.map(null)).isEmpty()
    }
}