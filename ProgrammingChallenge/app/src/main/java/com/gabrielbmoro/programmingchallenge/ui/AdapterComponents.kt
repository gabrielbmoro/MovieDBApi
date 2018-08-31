package com.gabrielbmoro.programmingchallenge.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.models.Movie
import com.squareup.picasso.Picasso

class CellSimpleMovieAdapter(alstMovies : ArrayList<Movie>): RecyclerView.Adapter<CellSimpleMovieViewHolder>() {

    var mlstMovies : ArrayList<Movie> = alstMovies
    var mpicasoObject : Picasso? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellSimpleMovieViewHolder {
        mpicasoObject = Picasso.Builder(parent.context).build()
        return CellSimpleMovieViewHolder((LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_simple_movie, parent, false)))
    }

    override fun getItemCount(): Int {
        return mlstMovies.size
    }

    override fun onBindViewHolder(holder: CellSimpleMovieViewHolder, position: Int) {
        val movieTarget = mlstMovies[position]
        holder.mtvTitle.text = movieTarget.mstrTitle
        holder.mtvReleaseDateValue.text = movieTarget.mstrReleaseDate
        holder.mtvVoteAverageValue.text = movieTarget.msVoteAverage.toString()
        holder.mtvOverView.text = movieTarget.mstrOverview
        holder.mivPoster

        holder.mllDetails.visibility = LinearLayout.GONE
        holder.mbtnExpand.setOnClickListener {
            if(holder.mllDetails.visibility == LinearLayout.GONE)
                holder.mllDetails.visibility = LinearLayout.VISIBLE
            else
                holder.mllDetails.visibility = LinearLayout.GONE
        }
        mpicasoObject?.load(movieTarget.mstrPosterPath)?.into(holder.mivPoster)
    }

}

class CellSimpleMovieViewHolder(avwView : View) : RecyclerView.ViewHolder(avwView) {
    val mtvTitle : TextView = avwView.findViewById(R.id.tvTitle)
    val mtvReleaseDateValue : TextView = avwView.findViewById(R.id.tvReleaseDateValue)
    val mtvVoteAverageValue : TextView = avwView.findViewById(R.id.tvVoteAverageValue)
    val mllDetails : LinearLayout = avwView.findViewById(R.id.llDetails)
    val mivPoster : ImageView = avwView.findViewById(R.id.ivPoster)
    val mtvOverView : TextView = avwView.findViewById(R.id.tvOverview)
    val mibFavorite : ImageButton = avwView.findViewById(R.id.ibFavorite)
    val mbtnExpand : Button = avwView.findViewById(R.id.btnExpand)

    init {
        mllDetails.visibility = LinearLayout.GONE
    }
}