package com.gabrielbmoro.programmingchallenge.models

enum class MoviesListType(val requestParameter : String) {

    POPULAR_RATED_MOVIES("popularity.desc"),
    TOP_RATED_MOVIES("vote_average.desc"),
    FAVORITE_MOVIES("");

    companion object {

        /**
         * This map connect the id and types
         */
        private val map = values().associateBy(MoviesListType::requestParameter)

        /**
         * Return some month type according to integer id
         * @param type is the integer id
         */
        fun fromRequestParameter(request: String) = map[request]

    }
}