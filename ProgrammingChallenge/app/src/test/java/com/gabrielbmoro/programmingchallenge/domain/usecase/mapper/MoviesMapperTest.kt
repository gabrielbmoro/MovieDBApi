package com.gabrielbmoro.programmingchallenge.domain.usecase.mapper

import com.gabrielbmoro.programmingchallenge.domain.model.Page
import com.gabrielbmoro.programmingchallenge.repository.api.response.MovieResponse
import com.gabrielbmoro.programmingchallenge.repository.api.response.PageResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesMapperTest {

    @Test
    fun whenTheResponseIsNull() {
        val given = null
        val result = MoviesMapper.mapToPage(given)
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
        val result = MoviesMapper.mapToPage(given)
        val expectedResult = Page(
                movies = emptyList(),
                hasMorePages = false
        )
        assertThat(result).isEqualTo(expectedResult)
        assertThat(expectedResult.hasMorePages).isFalse()
    }

    @Test
    fun whenMovieIsEmptyObject() {
        val given = MovieResponse(
                isVideo = null,
                releaseDate = null,
                overview = null,
                isAdult = null,
                backdropPath = null,
                originalLanguage = null,
                originalTitle = null,
                posterPath = null,
                popularity = null,
                votesAverage = null,
                title = null,
                votes = null
        )

        val result = MoviesMapper.mapToMovie(given)

        assertThat(result.isVideo).isFalse()
        assertThat(result.releaseDate).isEmpty()
        assertThat(result.overview).isEmpty()
        assertThat(result.isAdult).isFalse()
        assertThat(result.backdropPath).isEmpty()
        assertThat(result.originalLanguage).isEmpty()
        assertThat(result.originalTitle).isEmpty()
        assertThat(result.posterPath).isEmpty()
        assertThat(result.popularity).isZero()
        assertThat(result.votesAverage).isZero()
        assertThat(result.title).isEmpty()
        assertThat(result.votes).isEqualTo(0)
    }

    @Test
    fun whenMovieIsValidObject() {
        val given = MovieResponse(
                isVideo = false,
                releaseDate = "2019-20-02",
                overview = "A pair of troubled school boys navigate the chaos of love and friendship in the early 80's.",
                isAdult = false,
                backdropPath = null,
                originalLanguage = "en",
                originalTitle = "Choir Boyz",
                posterPath = "/gUbBFW1BlXSEmjEMJjMzU9XVKdM.jpg",
                popularity = 0f,
                votesAverage = 0f,
                title = "Choir Boyz",
                votes = 0
        )

        val result = MoviesMapper.mapToMovie(given)

        assertThat(result.isVideo).isFalse()
        assertThat(result.releaseDate).isEqualTo("02/20/2019")
        assertThat(result.overview).isEqualTo("A pair of troubled school boys navigate the chaos of love and friendship in the early 80's.")
        assertThat(result.isAdult).isFalse()
        assertThat(result.backdropPath).isEmpty()
        assertThat(result.originalLanguage).isEqualTo("en")
        assertThat(result.originalTitle).isEqualTo("Choir Boyz")
        assertThat(result.posterPath).isEqualTo("/gUbBFW1BlXSEmjEMJjMzU9XVKdM.jpg")
        assertThat(result.popularity).isZero()
        assertThat(result.votesAverage).isZero()
        assertThat(result.title).isEqualTo("Choir Boyz")
        assertThat(result.votes).isEqualTo(0)
    }

    @Test
    fun whenDateIsInInvalidFormat() {
        val given = "asodk-02-2012"

        val result = MoviesMapper.formatReleaseDate(given)

        val expectedResult = ""

        assertThat(expectedResult).isEqualTo(result)
    }


    @Test
    fun whenDateIsNull() {
        val given = null

        val result = MoviesMapper.formatReleaseDate(given)

        val expectedResult = ""

        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun whenDateIsValid() {
        val given = "2017-02-12"

        val result = MoviesMapper.formatReleaseDate(given)

        val expectedResult = "12/02/2017"

        assertThat(expectedResult).isEqualTo(result)
    }
}