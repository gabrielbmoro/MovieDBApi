package com.gabrielbmoro.programmingchallenge.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * This data class represents the Movie.
 * The annotations used was the Retrofit and Gson library.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
@Entity(tableName = "favorite_movie")
data class Movie(
        @SerializedName("vote_count") val votes: Int,
        @PrimaryKey(autoGenerate = false) @SerializedName("id") val id: Int,
        @SerializedName("video") var isVideo: Boolean,
        @SerializedName("vote_average") var votesAverage: Float,
        @SerializedName("title") var title: String,
        @SerializedName("popularity") var popularity: Float,
        @SerializedName("poster_path") var posterPath: String,
        @SerializedName("original_language") var originalLanguage: String,
        @SerializedName("original_title") var originalTitle: String,
        @SerializedName("backdrop_path") var backdropPath: String,
        @SerializedName("adult") var isAdult: Boolean,
        @SerializedName("overview") var overview: String,
        @SerializedName("release_date") var releaseDate: String,
        var isFavorite: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readFloat(),
            parcel.readString() ?: "",
            parcel.readFloat(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readByte() != 0.toByte(),
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(votes)
        parcel.writeInt(id)
        parcel.writeByte(if (isVideo) 1 else 0)
        parcel.writeFloat(votesAverage)
        parcel.writeString(title)
        parcel.writeFloat(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(backdropPath)
        parcel.writeByte(if (isAdult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}