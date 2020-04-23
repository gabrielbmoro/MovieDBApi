package com.gabrielbmoro.programmingchallenge.domain.usecase.mapper

import com.gabrielbmoro.programmingchallenge.domain.model.Page
import com.gabrielbmoro.programmingchallenge.repository.api.response.PageResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MoviesMapperTest {

    @Test
    fun whenTheResponseIsNull() {
        val given = null
        val result = MoviesMapper.map(given)
        assertThat(result).isNull()
    }

    @Test
    fun whenTheResponseIsEmpty() {
        val given = PageResponse(
                totalResults = 0,
                totalPages = 0,
                page = 0,
                results = emptyList()
        )
        val result = MoviesMapper.map(given)
        val expectedResult = Page(
                movies = emptyList(),
                hasMorePages = false
        )
        assertThat(result).isEqualTo(expectedResult)
    }
}