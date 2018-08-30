package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielbmoro.programmingchallenge.R

interface PopularMoviesPageContract {
    interface Presenter {

    }
    interface View{

    }
}

class PopularMoviesFragment : Fragment(), PopularMoviesPageContract.View {
    private var presenter : PopularMoviesPageContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_popularmovies, container, false)
    }
}

class PopularMoviesPresenter(aview : PopularMoviesPageContract.View) : PopularMoviesPageContract.Presenter {
    private val view : PopularMoviesPageContract.View = aview
}

