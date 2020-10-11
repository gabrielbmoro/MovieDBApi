package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.domain.model.Page
import com.gabrielbmoro.programmingchallenge.domain.usecase.mapper.MoviesMapper
import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularMoviesUseCase(val repository: MoviesRepository) {

    suspend fun execute(pageNumber: Int): Page? {
        return withContext(Dispatchers.IO){
            MoviesMapper.mapToPage(repository.getPopularMovies(pageNumber))
        }
    }
}