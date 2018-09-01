package com.gabrielbmoro.programmingchallenge.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie

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