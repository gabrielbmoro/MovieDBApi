package com.gabrielbmoro.programmingchallenge.koin.dataBase

import androidx.room.*
import com.gabrielbmoro.programmingchallenge.model.Movie

@Dao
interface FavoriteMoviesDAO {

    @Query("SELECT * FROM  favorite_movie")
    suspend fun allFavoriteMovies(): List<Movie>

    @Query("DELETE FROM favorite_movie WHERE id  = :movieId")
    suspend fun removeFavorite(movieId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(movie: Movie)

}