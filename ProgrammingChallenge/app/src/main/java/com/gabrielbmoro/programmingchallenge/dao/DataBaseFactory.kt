package com.gabrielbmoro.programmingchallenge.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie

/**
 * This class provides the interfaces to access the data base tables.
 *
 * @author Gabriel Moro
 * @since 02/09/2018
 */
@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class DataBaseFactory : RoomDatabase() {

    /**
     * Provides the data access object to
     * acess the table favoritemovies.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    abstract fun favoriteMovieDao(): FavoriteMovieDAO

}