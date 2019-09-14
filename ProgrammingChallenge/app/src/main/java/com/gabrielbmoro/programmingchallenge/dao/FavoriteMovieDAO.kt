package com.gabrielbmoro.programmingchallenge.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie
import com.gabrielbmoro.programmingchallenge.models.Movie

/**
 * This is the assistant provides the add and remove operations
 * using the data base factory object.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class FavoriteMovieDAOAssistant(adtBase: DataBaseFactory) {

    private val mdtBase: DataBaseFactory = adtBase

    /**
     * If there is some movie saved like favorite, this method
     * provides true.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun isThereSomeMovieInFavorite(amvMovie: Movie): Boolean {
        return mdtBase.favoriteMovieDao()
                .all()
                .filter { it.id == amvMovie.id }.count() > 0
    }

    /**
     * This method adds some movie as favorite movie.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun addMovieAsFavorite(amvMovie: Movie) {
        val bIsThereThisMovie = mdtBase.favoriteMovieDao()
                .all()
                .filter { it.id == amvMovie.id }
                .count() > 0
        if (!bIsThereThisMovie) {
            mdtBase.favoriteMovieDao().add(
                    FavoriteMovie(null,
                            amvMovie.id,
                            amvMovie.title,
                            amvMovie.releaseDate,
                            amvMovie.votesAverage)
            )
            amvMovie.isFavorite = true
        }
    }

    /**
     * This method delete some favorite movie.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun deleteFavoriteMovie(amvMovie: Movie) {
        val movieToRemove: FavoriteMovie? = mdtBase.favoriteMovieDao().all()
                .first { it.id == amvMovie.id }
        if (movieToRemove != null) {
            mdtBase.favoriteMovieDao().delete(movieToRemove)
            amvMovie.isFavorite = false
        }
    }
}

/**
 * This interface defines the base operations over expense table.
 *
 * @author Gabriel Moro
 * @since 02/09/2018
 */
@Dao
interface FavoriteMovieDAO {
    /**
     * Get all elements from movie table.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    @Query("SELECT * FROM favoritemovie")
    fun all(): List<FavoriteMovie>

    /**
     * Add some Movie element.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    @Insert
    fun add(vararg movie: FavoriteMovie)

    /**
     * Remove specific movie object.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    @Delete
    fun delete(movie: FavoriteMovie)
}