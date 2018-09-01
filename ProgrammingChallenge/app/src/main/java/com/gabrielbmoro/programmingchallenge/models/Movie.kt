package com.gabrielbmoro.programmingchallenge.models

import com.google.gson.annotations.SerializedName

/**
 * This data class represents the Movie.
 * The annotations used was the Retrofit and Gson library.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
data class Movie(
        @SerializedName("vote_count") val mnVote_count : Int,
        @SerializedName("id") val mnId : Int,
        @SerializedName("video") var mbIsItVideo : Boolean,
        @SerializedName("vote_average") var msVoteAverage : Float,
        @SerializedName("title") var mstrTitle : String,
        @SerializedName("popularity") var msPopularity : Float,
        @SerializedName("poster_path") var mstrPosterPath : String,
        @SerializedName("original_language") var mstrOriginalLanguage : String,
        @SerializedName("original_title") var mstrOriginalTitle : String,
        @SerializedName("genre_ids") var mlstGenreIds : List<Int>? = null,
        @SerializedName("backdrop_path") var mstrBackdropPath : String,
        @SerializedName("adult") var misAdult : Boolean,
        @SerializedName("overview") var mstrOverview : String,
        @SerializedName("release_date") var mstrReleaseDate : String,
        var mbIsFavorite : Boolean = false)