package com.gabrielbmoro.programmingchallenge.ui.homescreen.page.adapter

import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.ui.base.GeneralBaseAdapter

class MoviesListAdapter : GeneralBaseAdapter<MovieData>() {

    override fun layoutResourceId(): Int {
        return R.layout.view_holder_movie_card
    }

    fun setup(movieDataList: List<MovieData>?) {
        movieDataList?.let { lst ->
            if (elements == null)
                elements = ArrayList()
            else
                elements?.clear()
            elements?.addAll(lst)
            notifyDataSetChanged()
        }
    }

}