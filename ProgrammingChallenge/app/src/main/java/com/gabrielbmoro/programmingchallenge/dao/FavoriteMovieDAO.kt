package com.gabrielbmoro.programmingchallenge.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie
import com.gabrielbmoro.programmingchallenge.models.Movie

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
     */
    @Query("SELECT * FROM favoritemovie")
    fun all() : List<FavoriteMovie>

    /**
     * Add some Movie element.
     */
    @Insert
    fun add(vararg movie: FavoriteMovie)

    /**
     * Remove specific movie object.
     */
    @Delete
    fun delete(movie: FavoriteMovie)
}