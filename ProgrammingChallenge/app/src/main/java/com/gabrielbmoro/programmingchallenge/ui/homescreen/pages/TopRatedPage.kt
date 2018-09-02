package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.gabrielbmoro.programmingchallenge.ProgrammingChallengeApp
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess
import com.gabrielbmoro.programmingchallenge.dao.FavoriteMovieDAOAssistant
import com.gabrielbmoro.programmingchallenge.models.Movie
import com.gabrielbmoro.programmingchallenge.ui.CellSimpleMovieAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * This contract provides two interfaces:
 * - Presenter is used to manipulate the app's business
 * objects;
 * - View is used to control the widgets components.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
interface TopRatedPageContract {
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
        fun onNotifyDataChanged(moviesList : ArrayList<Movie>)
        fun showProgress()
        fun hideProgress()
        fun areThereSomeElementsAtRecyclerView() : Boolean
    }
}

/**
 * This is a view that represents the top rated movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class TopRatedFragment : Fragment(), TopRatedPageContract.View {

    private var presenter       : TopRatedPageContract.Presenter? = null
    private var mrvRecyclerView : RecyclerView? = null
    private var mpdProgressBar  : ProgressBar?  = null

    /**
     * In this state the fragment view is created.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_top_rated_movies, container, false)
    }

    /**
     * This state occurs after onCreateView.
     * In this state it is possible to create the widget instances.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrvRecyclerView = view?.findViewById(R.id.rvTopRatedMoviesList)
        mpdProgressBar = view?.findViewById(R.id.pbProgress)
        mpdProgressBar?.isIndeterminate = true
        setupRecyclerView()
        presenter = TopRatedPresenter(this)
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
        mrvRecyclerView?.adapter = CellSimpleMovieAdapter(ArrayList())
    }

    /**
     * This method allows to update all elements of the adapter list.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onNotifyDataChanged(amoviesList : ArrayList<Movie>) {
        (mrvRecyclerView?.adapter as CellSimpleMovieAdapter).mlstMovies = ArrayList(amoviesList)
        mrvRecyclerView?.adapter?.notifyDataSetChanged()
    }

    override fun hideProgress() {
        mpdProgressBar?.visibility = ProgressBar.GONE
    }

    override fun showProgress() {
        mpdProgressBar?.visibility = ProgressBar.VISIBLE
    }

    override fun areThereSomeElementsAtRecyclerView(): Boolean {
        return if(mrvRecyclerView?.adapter == null) false
        else {
            val cellSimpleAdapter = (mrvRecyclerView?.adapter as CellSimpleMovieAdapter)
            cellSimpleAdapter.mlstMovies.isNotEmpty()
        }
    }
}

/**
 * This is presenter provides all behavior to the its view.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class TopRatedPresenter(aview : TopRatedPageContract.View) : TopRatedPageContract.Presenter {

    private val view               : TopRatedPageContract.View = aview
            var mlstMoviesFiltered : ArrayList<Movie>? = null

    /**
     * This method allows the load movies using the request.
     * In this case, I use the RxJava to get the information
     * in IO thread, after that to update the screen in UI Thread.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun loadMovies() {
        if(!ProgrammingChallengeApp.mbHasNetworkConnection) return
        if(view.areThereSomeElementsAtRecyclerView()) return
        view.showProgress()
        val api = ApiServiceAccess()
        api.getMovies("vote_average.desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if(it.mlstResults != null) mlstMoviesFiltered = ArrayList(it.mlstResults!!)

                            val daoAssistant = FavoriteMovieDAOAssistant(ProgrammingChallengeApp.mappDataBuilder!!)
                            mlstMoviesFiltered?.forEach {
                                it.mstrPosterPath = "https://image.tmdb.org/t/p/w154"+ it.mstrPosterPath
                                if(ProgrammingChallengeApp.mappDataBuilder != null) {
                                    it.mbIsFavorite = daoAssistant.isThereSomeMovieInFavorite(it)
                                }
                            }
                        },
                        {
                            it.printStackTrace()
                        },{
                    //ui
                    if(mlstMoviesFiltered!=null) {
                        view.onNotifyDataChanged(mlstMoviesFiltered!!)
                        view.hideProgress()
                    }
                })
    }
}