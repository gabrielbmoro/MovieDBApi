package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.RxBus
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess
import com.gabrielbmoro.programmingchallenge.models.Movie
import com.gabrielbmoro.programmingchallenge.ui.CellSimpleMovieAdapter
import rx.Observer
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
interface PopularMoviesPageContract {
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
    }
}

/**
 * This is a view that represents the popular movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class PopularMoviesFragment : Fragment(), PopularMoviesPageContract.View {

    private var mrvRecyclerView : RecyclerView? = null
    private var presenter       : PopularMoviesPageContract.Presenter? = null
    private var mpdProgressBar  : ProgressBar?  = null

    /**
     * In this state the fragment view is created.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_popularmovies, container, false)
    }

    /**
     * This state occurs after onCreateView.
     * In this state it is possible to create the widget instances.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrvRecyclerView = view?.findViewById(R.id.rvPopularMoviesList)
        mpdProgressBar = view?.findViewById(R.id.pbProgress)
        mpdProgressBar?.isIndeterminate = true
        setupRecyclerView()
        presenter = PopularMoviesPresenter(this)

        RxBus.getConnectionEvent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    object : Observer<Boolean> {
                        override fun onCompleted() {

                        }
                        override fun onError(e: Throwable?) {

                        }
                        override fun onNext(t: Boolean?) {
                            if(it) {
                                Toast.makeText(context, "conectou", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
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
    override fun onNotifyDataChanged(amoviesList: ArrayList<Movie>) {
        (mrvRecyclerView?.adapter as CellSimpleMovieAdapter).mlstMovies = ArrayList(amoviesList)
        mrvRecyclerView?.adapter?.notifyDataSetChanged()
    }

    override fun hideProgress() {
        mpdProgressBar?.visibility = ProgressBar.GONE
    }

    override fun showProgress() {
        mpdProgressBar?.visibility = ProgressBar.VISIBLE
    }
}

/**
 * This is presenter provides all behavior to the its view.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class PopularMoviesPresenter(aview : PopularMoviesPageContract.View) : PopularMoviesPageContract.Presenter {

    private val view              : PopularMoviesPageContract.View = aview
            var mlstMoviesFiltered : ArrayList<Movie>? = null

    /**
     * This method allows the load movies using the request.
     * In this case, I use the RxJava to get the information
     * in IO thread, after that to update the screen in UI Thread.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun loadMovies() {
        view.showProgress()
        val api = ApiServiceAccess()
        api.getMovies("popularity.desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if(it.mlstResults != null) mlstMoviesFiltered = ArrayList(it.mlstResults!!)
                            mlstMoviesFiltered?.forEach {
                                it.mstrPosterPath = "https://image.tmdb.org/t/p/w154"+ it.mstrPosterPath
                            }
                        },
                        {
                            it.printStackTrace()
                        },{
                    //ui
                    if(mlstMoviesFiltered!=null) {
                        view.hideProgress()
                        view.onNotifyDataChanged(mlstMoviesFiltered!!)
                    }
                })
    }
}

