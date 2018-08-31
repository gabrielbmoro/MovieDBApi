package com.gabrielbmoro.programmingchallenge.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gabrielbmoro.programmingchallenge.ProgrammingChallengeApp
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie
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
        holder.mllDetails.visibility = LinearLayout.GONE
        if(movieTarget.mbIsFavorite)
            holder.mibFavorite.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
        else
            holder.mibFavorite.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)

        holder.mbtnExpand.setOnClickListener {
            if(holder.mllDetails.visibility == LinearLayout.GONE)
                holder.mllDetails.visibility = LinearLayout.VISIBLE
            else
                holder.mllDetails.visibility = LinearLayout.GONE
        }
        mpicasoObject?.load(movieTarget.mstrPosterPath)?.into(holder.mivPoster)
        holder.mibFavorite.setOnClickListener {
            val bNewValue = !movieTarget.mbIsFavorite
            if(bNewValue) {
                ProgrammingChallengeApp.mappDataBuilder?.favoriteMovieDao()?.add(FavoriteMovie(null,
                        mlstMovies[position].mnId,
                        mlstMovies[position].mstrTitle,
                        mlstMovies[position].mstrReleaseDate,
                        mlstMovies[position].msVoteAverage))
                mlstMovies[position].mbIsFavorite = true
                notifyItemChanged(position)
            } else {
                val databaseLst = ProgrammingChallengeApp.mappDataBuilder?.favoriteMovieDao()?.all()
                val fvriteMovie = databaseLst?.filter { fvite -> fvite.mnId == mlstMovies[position].mnId }?.first()
                if(fvriteMovie!=null) {
                    ProgrammingChallengeApp.mappDataBuilder?.favoriteMovieDao()?.delete(fvriteMovie)
                    mlstMovies[position].mbIsFavorite = false
                    notifyItemChanged(position)
                }
            }
        }
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
        mibFavorite.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
    }
}

class CellFavoriteMovieAdapter(alstMovies : ArrayList<FavoriteMovie>): RecyclerView.Adapter<CellFavoriteMovieViewHolder>() {
    var mlstMovies : ArrayList<FavoriteMovie> = alstMovies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellFavoriteMovieViewHolder {
        return CellFavoriteMovieViewHolder((LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_favorite_movie, parent, false)))
    }

    override fun getItemCount(): Int {
        return mlstMovies.size
    }

    override fun onBindViewHolder(holder: CellFavoriteMovieViewHolder, position: Int) {
        val movieTarget = mlstMovies[position]
        holder.mtvTitle.text = movieTarget.mstrTitle
        holder.mtvReleaseDateValue.text = movieTarget.mstrReleaseDate
        holder.mtvVoteAverageValue.text = movieTarget.msVoteAverage.toString()
    }
}

class CellFavoriteMovieViewHolder(avwView : View) : RecyclerView.ViewHolder(avwView) {
    val mtvTitle : TextView = avwView.findViewById(R.id.tvTitle)
    val mtvReleaseDateValue : TextView = avwView.findViewById(R.id.tvReleaseDateValue)
    val mtvVoteAverageValue : TextView = avwView.findViewById(R.id.tvVoteAverageValue)
}