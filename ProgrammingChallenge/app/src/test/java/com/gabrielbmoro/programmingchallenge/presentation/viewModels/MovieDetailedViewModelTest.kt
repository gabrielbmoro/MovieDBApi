package com.gabrielbmoro.programmingchallenge.presentation.viewModels

import com.gabrielbmoro.programmingchallenge.KoinUnitTest
import com.gabrielbmoro.programmingchallenge.domain.model.Movie
import com.gabrielbmoro.programmingchallenge.domain.usecase.FavoriteMovieUseCase
import com.gabrielbmoro.programmingchallenge.presentation.detailedScreen.MovieDetailedViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.times

class MovieDetailedViewModelTest : KoinUnitTest() {

    lateinit var viewModel: MovieDetailedViewModel
    private val favoriteMovieUseCase by inject<FavoriteMovieUseCase>()

    @Before
    fun init() {
        viewModel = MovieDetailedViewModel(favoriteMovieUseCase)
    }

    @Test
    fun whenFavoriteActionIsSelected() {
        val useCaseSpy = Mockito.spy(favoriteMovieUseCase)
        val given = emptyMovieObj()
        viewModel.setup(given)
        viewModel.favoriteEvent(true)
        GlobalScope.launch {
            Mockito.verify(useCaseSpy, times(1)).favoriteMovie(given)
        }
    }

    @Test
    fun whenUnFavoriteActionIsSelected() {
        val useCaseSpy = Mockito.spy(favoriteMovieUseCase)
        val given = emptyMovieObj()
        viewModel.setup(given)
        viewModel.favoriteEvent(false)
        GlobalScope.launch {
            Mockito.verify(useCaseSpy, times(1)).unFavoriteMovie(given)
        }
    }

    private fun emptyMovieObj(): Movie {
        return Movie(
                id = null,
                votes = 0,
                isVideo = false,
                votesAverage = 0f,
                title = "",
                popularity = 0f,
                posterPath = "",
                originalLanguage = "",
                originalTitle = "",
                backdropPath = "",
                isAdult = false,
                overview = "",
                releaseDate = "",
                isFavorite = false
        )
    }

    @Test
    fun whenValidMovieIsSelected() {
        val movieId = -1
        val votes = 4
        val isVideo = false
        val votesAverage = 2f
        val title = "Ad Astra"
        val popularity = 2f
        val posterPath = ""
        val originalLanguage = "en-US"
        val backdropPath = ""
        val isAdult = false
        val overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
                "fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " +
                "sunt in culpa qui officia deserunt mollit anim id est laborum."
        val releaseDate = "02-02-2012"
        val isFavorite = true
        val given = Movie(
                id = movieId,
                votes = votes,
                isVideo = isVideo,
                votesAverage = votesAverage,
                title = title,
                popularity = popularity,
                posterPath = posterPath,
                originalLanguage = originalLanguage,
                originalTitle = title,
                backdropPath = backdropPath,
                isAdult = isAdult,
                overview = overview,
                releaseDate = releaseDate,
                isFavorite = isFavorite
        )
        viewModel.setup(given)
        assertThat(viewModel.getMovie()?.id).isEqualTo(movieId)
        assertThat(viewModel.getMovie()?.votes).isEqualTo(votes)
        assertThat(viewModel.getMovie()?.isVideo).isEqualTo(isVideo)
        assertThat(viewModel.getMovie()?.votesAverage).isEqualTo(votesAverage)
        assertThat(viewModel.getMovie()?.title).isEqualTo(title)
        assertThat(viewModel.getMovie()?.originalLanguage).isEqualTo(originalLanguage)
        assertThat(viewModel.getMovie()?.originalTitle).isEqualTo(title)
        assertThat(viewModel.getMovie()?.popularity).isEqualTo(popularity)
        assertThat(viewModel.getMovie()?.posterPath).isEqualTo(posterPath)
        assertThat(viewModel.getMovie()?.backdropPath).isEqualTo(backdropPath)
        assertThat(viewModel.getMovie()?.isAdult).isEqualTo(isAdult)
        assertThat(viewModel.getMovie()?.overview).isEqualTo(overview)
        assertThat(viewModel.getMovie()?.releaseDate).isEqualTo(releaseDate)
        assertThat(viewModel.getMovie()?.isFavorite).isEqualTo(isFavorite)
    }

}