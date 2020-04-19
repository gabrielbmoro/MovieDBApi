package com.gabrielbmoro.programmingchallenge.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * This data class represents the Movie.
 * The annotations used was the Retrofit and Gson library.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
@Entity(tableName = "favorite_movies")
@Parcelize
open class Movie(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("id")
        var id: Int,
        open val votes: Int,
        open val isVideo: Boolean,
        open val votesAverage: Float,
        open val title: String,
        open val popularity: Float,
        open val posterPath: String,
        open val originalLanguage: String,
        open val originalTitle: String,
        open val backdropPath: String,
        open val isAdult: Boolean,
        open val overview: String,
        open val releaseDate: String
) : Parcelable