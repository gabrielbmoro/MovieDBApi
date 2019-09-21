package com.gabrielbmoro.programmingchallenge.koin.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gabrielbmoro.programmingchallenge.model.Movie

@Dao
interface FavoriteMoviesDAO {

    @Query("SELECT * FROM  favorite_movie")
    suspend fun allFavoriteMovies(): List<Movie>

    @Query("DELETE FROM favorite_movie WHERE dataBaseId  = :dataBaseId")
    suspend fun removeFavorite(dataBaseId: Long)

    @Insert
    suspend fun saveFavorite(movie: Movie)

}