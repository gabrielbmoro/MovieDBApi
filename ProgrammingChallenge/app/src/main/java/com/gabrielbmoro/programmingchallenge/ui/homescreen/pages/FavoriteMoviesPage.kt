package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielbmoro.programmingchallenge.R

interface FavoriteMoviesPageContract {
    interface Presenter {

    }
    interface View{

    }
}

class FavoriteMoviesFragment : Fragment(), FavoriteMoviesPageContract.View {
    private var presenter : FavoriteMoviesPageContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_favoritemovies, container, false)
    }
}

class FavoriteMoviesPresenter(aview : FavoriteMoviesPageContract.View) : FavoriteMoviesPageContract.Presenter {
    private val view : FavoriteMoviesPageContract.View = aview
}

