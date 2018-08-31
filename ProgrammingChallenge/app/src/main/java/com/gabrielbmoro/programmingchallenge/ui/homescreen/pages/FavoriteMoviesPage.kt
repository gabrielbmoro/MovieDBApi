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

interface FavoriteMoviesPageContract {
    interface Presenter {
        fun loadMovies()
    }
    interface View{
        fun setupRecyclerView()
        fun onNotifyDataChanged(amoviesList : ArrayList<FavoriteMovie>)
    }
}

class FavoriteMoviesFragment : Fragment(), FavoriteMoviesPageContract.View {

    private var mrvRecyclerView : RecyclerView? = null
    private var presenter : FavoriteMoviesPageContract.Presenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_favoritemovies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrvRecyclerView = view?.findViewById(R.id.rvFavoriteMoviesList)
        setupRecyclerView()
        presenter = FavoriteMoviesPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadMovies()
    }

    override fun setupRecyclerView() {
        val llManager = LinearLayoutManager(context)
        mrvRecyclerView?.layoutManager = llManager
        mrvRecyclerView?.adapter = CellFavoriteMovieAdapter(ArrayList())
    }

    override fun onNotifyDataChanged(amoviesList: ArrayList<FavoriteMovie>) {
        (mrvRecyclerView?.adapter as CellFavoriteMovieAdapter).mlstMovies = ArrayList(amoviesList)
        mrvRecyclerView?.adapter?.notifyDataSetChanged()
    }

}

class FavoriteMoviesPresenter(aview : FavoriteMoviesPageContract.View) : FavoriteMoviesPageContract.Presenter {
    private val view : FavoriteMoviesPageContract.View = aview

    /*override fun loadMovies() {
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
    }*/

    override fun loadMovies() {
        val lstFavoriteMovies = ProgrammingChallengeApp.mappDataBuilder?.favoriteMovieDao()?.all()
        if(lstFavoriteMovies!=null) view.onNotifyDataChanged(ArrayList(lstFavoriteMovies))
    }
}

