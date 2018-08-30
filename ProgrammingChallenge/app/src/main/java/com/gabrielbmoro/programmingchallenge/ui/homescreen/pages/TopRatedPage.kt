package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess
import com.gabrielbmoro.programmingchallenge.models.Movie
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

interface TopRatedPageContract {
    interface Presenter {
        fun loadMovies()
    }
    interface View{
        fun setupRecyclerView()
        fun onNotifyDataChanged(moviesList : ArrayList<Movie>)
    }
}

class TopRatedFragment : Fragment(), TopRatedPageContract.View {
    private var presenter : TopRatedPageContract.Presenter? = null
    private var mrvRecyclerView : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_topratedmovies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mrvRecyclerView = view?.findViewById(R.id.rvTopRatedMoviesList)
        setupRecyclerView()
        presenter = TopRatedPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadMovies()
    }

    override fun setupRecyclerView() {
        val llManager = LinearLayoutManager(context)
        mrvRecyclerView?.layoutManager = llManager
        mrvRecyclerView?.adapter = TopRatedMoviesAdapter(ArrayList())
    }

    override fun onNotifyDataChanged(amoviesList : ArrayList<Movie>) {
        (mrvRecyclerView?.adapter as TopRatedMoviesAdapter).mlstMovies = ArrayList(amoviesList)
        mrvRecyclerView?.adapter?.notifyDataSetChanged()
    }

    inner class TopRatedMoviesViewHolder(avwView : View) : RecyclerView.ViewHolder(avwView) {
        val mtvTitle : TextView = avwView.findViewById(R.id.tvTitle)
        val mtvVoteAverage : TextView = avwView.findViewById(R.id.tvVoteAverage)
    }
    inner class TopRatedMoviesAdapter(alstMovies : ArrayList<Movie>): RecyclerView.Adapter<TopRatedMoviesViewHolder>() {

        var mlstMovies : ArrayList<Movie> = alstMovies

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
            return TopRatedMoviesViewHolder((LayoutInflater.from(parent.context)
                    .inflate(R.layout.cell_topratedmovie, parent, false)))
        }

        override fun getItemCount(): Int {
            return mlstMovies.size
        }

        override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
            val movieTarget = mlstMovies[position]
            holder.mtvTitle.text = movieTarget.mstrTitle
            holder.mtvVoteAverage.text = movieTarget.mnVoteAverage.toString()
        }

    }
}

class TopRatedPresenter(aview : TopRatedPageContract.View) : TopRatedPageContract.Presenter {

    private val view : TopRatedPageContract.View = aview
    var mlstMoviesFiltered : ArrayList<Movie>? = null

    override fun loadMovies() {
        val api = ApiServiceAccess()
        api.getMovies("vote_average.desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if(it.mlstResults != null) mlstMoviesFiltered = ArrayList(it.mlstResults!!)
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