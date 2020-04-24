package com.gabrielbmoro.programmingchallenge.domain.model

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat

@RunWith(MockitoJUnitRunner::class)
class MoviesListTypeTest {

    @Test
    fun whenTheMovieIsATopRatedMovie() {
        val given = 1

        val result = given.convertToMovieListType()

        assertThat(result).isEqualTo(MovieListType.TopRated)
    }

    @Test
    fun whenTheMovieIsAFavoriteMovie() {
        val given = 2

        val result = given.convertToMovieListType()

        assertThat(result).isEqualTo(MovieListType.Favorite)
    }

    @Test
    fun whenTheMovieIsAPopularMovie() {
        val given = 3

        val result = given.convertToMovieListType()

        assertThat(result).isEqualTo(MovieListType.Popular)
    }

}