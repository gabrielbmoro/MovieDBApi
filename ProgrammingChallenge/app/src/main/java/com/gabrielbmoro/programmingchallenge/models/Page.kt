package com.gabrielbmoro.programmingchallenge.models

import com.google.gson.annotations.SerializedName

/**
 * This data class represents a Page that has a lot of movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
data class Page(
        @SerializedName("page") val mnPageId : Int,
        @SerializedName("total_results") var mnTotalResults : Int,
        @SerializedName("total_pages") var mnTotalPages : Int,
        @SerializedName("results") var mlstResults : List<Movie>? = null)