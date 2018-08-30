package com.gabrielbmoro.programmingchallenge.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.api.ApiServiceAccess
import com.gabrielbmoro.programmingchallenge.models.Movie
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    var mlstMoviesFiltered : ArrayList<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                            mlstMoviesFiltered?.forEach { Log.d("teste", it.toString()) }
                        }
                )
        //?sort_by=vote_average.desc
        //?sort_by=popularity.desc


    }
}
