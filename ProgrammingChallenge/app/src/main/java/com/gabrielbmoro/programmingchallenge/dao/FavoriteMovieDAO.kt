package com.gabrielbmoro.programmingchallenge.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie
import com.gabrielbmoro.programmingchallenge.models.Movie

/**
 * This is the assistant provides the add and remove operations
 * using the data base factory object.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class FavoriteMovieDAOAssistant(adtBase : DataBaseFactory) {

    private val mdtBase : DataBaseFactory = adtBase

    /**
     * If there is some movie saved like favorite, this method
     * provides true.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun isThereSomeMovieInFavorite(amvMovie: Movie) : Boolean {
        return mdtBase.favoriteMovieDao()
                .all()
                .filter { it.mnId == amvMovie.mnId }.count() > 0
    }
    /**
     * This method adds some movie as favorite movie.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun addMovieAsFavorite(amvMovie : Movie){
        val bIsThereThisMovie = mdtBase.favoriteMovieDao()
                .all()
                .filter { it.mnId == amvMovie.mnId }
                .count() > 0
        if(!bIsThereThisMovie) {
            mdtBase.favoriteMovieDao().add(
                    FavoriteMovie(null,
                            amvMovie.mnId,
                            amvMovie.mstrTitle,
                            amvMovie.mstrReleaseDate,
                            amvMovie.msVoteAverage)
            )
            amvMovie.mbIsFavorite = true
        }
    }

    /**
     * This method delete some favorite movie.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    fun deleteFavoriteMovie(amvMovie : Movie) {
        val movieToRemove : FavoriteMovie? = mdtBase.favoriteMovieDao().all()
                .first{ it.mnId == amvMovie.mnId }
        if(movieToRemove != null) {
            mdtBase.favoriteMovieDao().delete(movieToRemove)
            amvMovie.mbIsFavorite = false
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
    fun all() : List<FavoriteMovie>

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