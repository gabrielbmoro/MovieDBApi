package com.gabrielbmoro.programmingchallenge.models

import com.google.gson.annotations.SerializedName

/**
 * This data class represents the Movie.
 * The annotations used was the Retrofit and Gson library.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
data class Movie(
        @SerializedName("vote_count") val votes: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("video") var isVideo: Boolean,
        @SerializedName("vote_average") var votesAverage: Float,
        @SerializedName("title") var title: String,
        @SerializedName("popularity") var popularity: Float,
        @SerializedName("poster_path") var posterPath: String,
        @SerializedName("original_language") var originalLanguage: String,
        @SerializedName("original_title") var originalTitle: String,
        @SerializedName("genre_ids") var genreIds: List<Int>? = null,
        @SerializedName("backdrop_path") var backdropPath: String,
        @SerializedName("adult") var isAdult: Boolean,
        @SerializedName("overview") var overview: String,
        @SerializedName("release_date") var releaseDate: String,
        var isFavorite: Boolean = false
)