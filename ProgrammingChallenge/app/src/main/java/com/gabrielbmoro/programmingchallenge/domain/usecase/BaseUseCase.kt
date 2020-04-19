package com.gabrielbmoro.programmingchallenge.domain.usecase

import com.gabrielbmoro.programmingchallenge.repository.MoviesRepository

abstract class BaseUseCase(val repository: MoviesRepository)