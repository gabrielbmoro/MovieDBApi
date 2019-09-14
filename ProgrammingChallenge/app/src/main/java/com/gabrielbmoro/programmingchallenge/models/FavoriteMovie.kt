package com.gabrielbmoro.programmingchallenge.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class represents the movie choosed by
 * User to be its favorite movie.
 * The annotations used was the RoomLibrary.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
@Entity(tableName = "favoriteMovie")
data class FavoriteMovie(
        @PrimaryKey(autoGenerate = true) val dataBaseId: Long?,
        @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "releasedate") var releaseDate: String,
        @ColumnInfo(name = "voteaverage") var votesAverage: Float
)