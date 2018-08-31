package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess
import com.gabrielbmoro.programmingchallenge.models.Movie
import com.gabrielbmoro.programmingchallenge.ui.CellSimpleMovieAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

interface PopularMoviesPageContract {
    interface Presenter {
        fun loadMovies()
    }
    interface View{
        fun setupRecyclerView()
        fun onNotifyDataChanged(moviesList : ArrayList<Movie>)
    }
}

class PopularMoviesFragment : Fragment(), PopularMoviesPageContract.View {

    private var mrvRecyclerView : RecyclerView? = null
    private var presenter : PopularMoviesPageContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_popularmovies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrvRecyclerView = view?.findViewById(R.id.rvPopularMoviesList)
        setupRecyclerView()
        presenter = PopularMoviesPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadMovies()
    }

    override fun setupRecyclerView() {
        val llManager = LinearLayoutManager(context)
        mrvRecyclerView?.layoutManager = llManager
        mrvRecyclerView?.adapter = CellSimpleMovieAdapter(ArrayList())
    }

    override fun onNotifyDataChanged(amoviesList: ArrayList<Movie>) {
        (mrvRecyclerView?.adapter as CellSimpleMovieAdapter).mlstMovies = ArrayList(amoviesList)
        mrvRecyclerView?.adapter?.notifyDataSetChanged()
    }
}

class PopularMoviesPresenter(aview : PopularMoviesPageContract.View) : PopularMoviesPageContract.Presenter {

    private val view : PopularMoviesPageContract.View = aview
    var mlstMoviesFiltered : ArrayList<Movie>? = null

    override fun loadMovies() {
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
                    if(mlstMoviesFiltered!=null)
                        view.onNotifyDataChanged(mlstMoviesFiltered!!)
                }
                )
    }
}

