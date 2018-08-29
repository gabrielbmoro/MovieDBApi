package com.gabrielbmoro.programmingchallenge.models

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("vote_count") val mnVote_count : Int,
        @SerializedName("id") val mnId : Int,
        @SerializedName("video") var mbIsItVideo : Boolean,
        @SerializedName("vote_average") var mnVoteAverage : Int,
        @SerializedName("title") var mstrTitle : String,
        @SerializedName("popularity") var msPopularity : Float,
        @SerializedName("poster_path") var mstrPosterPath : String,
        @SerializedName("original_language") var mstrOriginalLanguage : String,
        @SerializedName("original_title") var mstrOriginalTitle : String,
        @SerializedName("genre_ids") var mlstGenreIds : List<Int>? = null,
        @SerializedName("backdrop_path") var mstrBackdropPath : String,
        @SerializedName("adult") var misAdult : Boolean,
        @SerializedName("overview") var mstrOverview : String,
        @SerializedName("release_date") var mstrReleaseDate : String)