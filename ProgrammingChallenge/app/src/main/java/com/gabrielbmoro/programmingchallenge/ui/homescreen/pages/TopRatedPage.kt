package com.gabrielbmoro.programmingchallenge.ui.homescreen.pages

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.gabrielbmoro.programmingchallenge.ProgrammingChallengeApp
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess
import com.gabrielbmoro.programmingchallenge.dao.FavoriteMovieDAOAssistant
import com.gabrielbmoro.programmingchallenge.databinding.FragmentTopRatedMoviesBinding
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
    interface View {
        fun setupRecyclerView()
        fun onNotifyDataChanged(moviesList: ArrayList<Movie>)
        fun showProgress()
        fun hideProgress()
        fun areThereSomeElementsAtRecyclerView(): Boolean
    }
}

/**
 * This is a view that represents the top rated movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class TopRatedFragment : androidx.fragment.app.Fragment(), TopRatedPageContract.View {

    private lateinit var binding : FragmentTopRatedMoviesBinding
    private var presenter: TopRatedPageContract.Presenter? = null

    /**
     * In this state the fragment view is created.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated_movies, container, false)
        return binding.root
    }

    /**
     * This state occurs after onCreateView.
     * In this state it is possible to create the widget instances.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pbProgress.isIndeterminate = true
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
        binding.rvTopRatedMoviesList.layoutManager = llManager
        binding.rvTopRatedMoviesList.adapter = CellSimpleMovieAdapter(ArrayList())
    }

    /**
     * This method allows to update all elements of the adapter list.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onNotifyDataChanged(moviesList: ArrayList<Movie>) {
        (binding.rvTopRatedMoviesList.adapter as CellSimpleMovieAdapter).mlstMovies = ArrayList(moviesList)
        binding.rvTopRatedMoviesList.adapter?.notifyDataSetChanged()
    }

    override fun hideProgress() {
        binding.pbProgress.visibility = ProgressBar.GONE
    }

    override fun showProgress() {
        binding.pbProgress.visibility = ProgressBar.VISIBLE
    }

    override fun areThereSomeElementsAtRecyclerView(): Boolean {
        return if (binding.rvTopRatedMoviesList.adapter == null) false
        else {
            val cellSimpleAdapter = (binding.rvTopRatedMoviesList.adapter as CellSimpleMovieAdapter)
            cellSimpleAdapter.mlstMovies.isNotEmpty()
        }
    }
}

/**
 * This is presenter provides all behavior to the its view.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class TopRatedPresenter(aview: TopRatedPageContract.View) : TopRatedPageContract.Presenter {

    private val view: TopRatedPageContract.View = aview
    private var mlstMoviesFiltered: ArrayList<Movie>? = null

    /**
     * This method allows the load movies using the request.
     * In this case, I use the RxJava to get the information
     * in IO thread, after that to update the screen in UI Thread.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun loadMovies() {
        if (!ProgrammingChallengeApp.mbHasNetworkConnection) return
        if (view.areThereSomeElementsAtRecyclerView()) return
        view.showProgress()
        val api = ApiServiceAccess()
        api.getMovies("vote_average.desc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it.results != null) mlstMoviesFiltered = ArrayList(it.results!!)

                            val daoAssistant = FavoriteMovieDAOAssistant(ProgrammingChallengeApp.mappDataBuilder!!)
                            mlstMoviesFiltered?.forEach { movie ->
                                movie.posterPath = "https://image.tmdb.org/t/p/w154" + movie.posterPath
                                if (ProgrammingChallengeApp.mappDataBuilder != null) {
                                    movie.isFavorite = daoAssistant.isThereSomeMovieInFavorite(movie)
                                }
                            }
                        },
                        {
                            it.printStackTrace()
                        }, {
                    //ui
                    if (mlstMoviesFiltered != null) {
                        view.onNotifyDataChanged(mlstMoviesFiltered!!)
                        view.hideProgress()
                    }
                })
    }
}