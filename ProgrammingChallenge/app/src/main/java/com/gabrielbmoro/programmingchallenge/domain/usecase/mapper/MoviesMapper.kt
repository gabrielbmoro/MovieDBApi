package com.gabrielbmoro.programmingchallenge.domain.usecase.mapper

import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.repository.api.response.PageResponse

object MoviesMapper{
    fun map(response : PageResponse?) : List<Movie>{
        return emptyList()
    }
}