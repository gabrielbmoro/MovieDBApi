package com.gabrielbmoro.programmingchallenge.domain.usecase.mapper

import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.repository.api.response.PageResponse

object MoviesMapper {
    fun map(response: PageResponse?): List<Movie> {
        return response?.results?.map {
            Movie(
                    id = 0,
                    votes = it.votes ?: 0,
                    votesAverage = it.votesAverage ?: 0f,
                    isVideo = it.isVideo ?: false,
                    releaseDate = it.releaseDate ?: "",
                    overview = it.overview ?: "",
                    backdropPath = it.backdropPath ?: "",
                    isAdult = it.isAdult ?: false,
                    originalTitle = it.originalTitle ?: "",
                    originalLanguage = it.originalLanguage ?: "",
                    posterPath = it.posterPath ?: "",
                    popularity = it.popularity ?: 0f,
                    title = it.title ?: ""
            )
        } ?: emptyList()
    }
}