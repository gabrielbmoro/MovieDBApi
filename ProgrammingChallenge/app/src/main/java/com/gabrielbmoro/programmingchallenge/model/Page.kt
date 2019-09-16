package com.gabrielbmoro.programmingchallenge.model

import com.google.gson.annotations.SerializedName

/**
 * This data class represents a Page that has a lot of movies.
 * The annotations used was the Retrofit and Gson library.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
data class Page(
        @SerializedName("page") val page: Int,
        @SerializedName("total_results") var totalResults: Int,
        @SerializedName("total_pages") var totalPages: Int,
        @SerializedName("results") var results: List<Movie>? = null
)