package com.gabrielbmoro.programmingchallenge.models

import com.google.gson.annotations.SerializedName

data class Page(
        @SerializedName("page") val mnPageId : Int,
        @SerializedName("total_results") var mnTotalResults : Int,
        @SerializedName("total_pages") var mnTotalPages : Int,
        @SerializedName("results") var mlstResults : List<Movie>? = null)