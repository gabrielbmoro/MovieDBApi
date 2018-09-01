package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielbmoro.programmingchallenge.ProgrammingChallengeApp
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie
import com.gabrielbmoro.programmingchallenge.ui.CellFavoriteMovieAdapter

/**
 * This contract provides two interfaces:
 * - Presenter is used to manipulate the app's business
 * objects;
 * - View is used to control the widgets components.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
interface FavoriteMoviesPageContract {
    /**
     * Presenter defines the load movies action.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    interface Presenter {
        fun loadMovies()
    }

    /**
     * View defines the setup and update screen operations.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    interface View{
        fun setupRecyclerView()
        fun onNotifyDataChanged(amoviesList : ArrayList<FavoriteMovie>)
    }
}

/**
 * This is a view that represents the favorite movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class FavoriteMoviesFragment : Fragment(), FavoriteMoviesPageContract.View {

    private var mrvRecyclerView : RecyclerView?           = null
    private var presenter       : FavoriteMoviesPageContract.Presenter? = null

    /**
     * In this state the fragment view is created.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_favoritemovies, container, false)
    }

    /**
     * This state occurs after onCreateView.
     * In this state it is possible to create the widget instances.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrvRecyclerView = view?.findViewById(R.id.rvFavoriteMoviesList)
        setupRecyclerView()
        presenter = FavoriteMoviesPresenter(this)
    }

    /**
     * The last state before show the page to the user.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onResume() {
        super.onResume()
        presenter?.loadMovies()
    }

    /**
     * This method start the first recyclerview setup
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun setupRecyclerView() {
        val llManager = LinearLayoutManager(context)
        mrvRecyclerView?.layoutManager = llManager
        mrvRecyclerView?.adapter = CellFavoriteMovieAdapter(ArrayList())
    }

    /**
     * This method allows to update all elements of the adapter list.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onNotifyDataChanged(amoviesList: ArrayList<FavoriteMovie>) {
        (mrvRecyclerView?.adapter as CellFavoriteMovieAdapter).mlstMovies = ArrayList(amoviesList)
        mrvRecyclerView?.adapter?.notifyDataSetChanged()
    }
}

/**
 * This is presenter provides all behavior to the its view.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class FavoriteMoviesPresenter(aview : FavoriteMoviesPageContract.View) : FavoriteMoviesPageContract.Presenter {
    private val view : FavoriteMoviesPageContract.View = aview

    /**
     * This method allows the load movies from data base.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun loadMovies() {
        val lstFavoriteMovies = ProgrammingChallengeApp.mappDataBuilder?.favoriteMovieDao()?.all()
        if(lstFavoriteMovies!=null) view.onNotifyDataChanged(ArrayList(lstFavoriteMovies))
    }
}