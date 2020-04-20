package com.gabrielbmoro.programmingchallenge.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var selectedPage = TOP_RATED_PAGE

    fun setPage(pageIndex: Int) {
        selectedPage = pageIndex
    }

    fun getPage() = selectedPage

    companion object {
        const val TOP_RATED_PAGE = 0
        const val POPULAR_PAGE = 1
        const val FAVORITE_PAGE = 2
    }
}