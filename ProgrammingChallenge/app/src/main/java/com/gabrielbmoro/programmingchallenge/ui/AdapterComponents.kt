package com.gabrielbmoro.programmingchallenge.ui

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gabrielbmoro.programmingchallenge.ProgrammingChallengeApp
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.dao.FavoriteMovieDAOAssistant
import com.gabrielbmoro.programmingchallenge.models.FavoriteMovie
import com.gabrielbmoro.programmingchallenge.models.Movie
import com.squareup.picasso.Picasso

/**
 * This adapter is the manager of R.layout.cell_simple_movie.
 * It allows the content configuration according the list of
 * model objects.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class CellSimpleMovieAdapter(alstMovies : ArrayList<Movie>):RecyclerView.Adapter<CellSimpleMovieViewHolder>() {

    var mlstMovies: ArrayList<Movie> = alstMovies
    private var mpicasoObject: Picasso? = null

    /**
     * This state is necessary to create the viewholder. This
     * object will be used to send new content
     * using adapter notifications.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellSimpleMovieViewHolder {
        mpicasoObject = Picasso.Builder(parent.context).build()
        return CellSimpleMovieViewHolder((LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_simple_movie, parent, false)))
    }

    /**
     * Return the size number of list objects. This
     * control is used by onBindViewHolder.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun getItemCount(): Int {
        return mlstMovies.size
    }

    /**
     * This method allows to relation the object and viewholder.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onBindViewHolder(holder: CellSimpleMovieViewHolder, position: Int) {
        val movieTarget = mlstMovies[position]
        holder.mtvTitle.text = movieTarget.title
        holder.mtvReleaseDateValue.text = movieTarget.releaseDate
        holder.mtvVoteAverageValue.text = movieTarget.votesAverage.toString()
        holder.mtvOverView.text = movieTarget.overview
        holder.mllDetails.visibility = LinearLayout.GONE
        holder.mbtnExpand.setText(R.string.expand)
        if (movieTarget.isFavorite)
            holder.mibFavorite.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
        else
            holder.mibFavorite.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)

        holder.mbtnExpand.setOnClickListener {
            if (holder.mllDetails.visibility == LinearLayout.GONE) {
                holder.mllDetails.visibility = LinearLayout.VISIBLE
                holder.mbtnExpand.setText(R.string.hide)
            } else {
                holder.mllDetails.visibility = LinearLayout.GONE
                holder.mbtnExpand.setText(R.string.expand)
            }
        }


        mpicasoObject?.load(movieTarget.posterPath)?.into(holder.mivPoster)


        holder.mibFavorite.setOnClickListener {
            val bNewValue = !movieTarget.isFavorite
            val databaseInstance = ProgrammingChallengeApp.mappDataBuilder
            if (databaseInstance != null) {
                if (bNewValue) {
                    FavoriteMovieDAOAssistant(databaseInstance)
                            .addMovieAsFavorite(mlstMovies[position])
                    notifyItemChanged(position)
                } else {
                    FavoriteMovieDAOAssistant(databaseInstance)
                            .deleteFavoriteMovie(mlstMovies[position])
                    notifyItemChanged(position)
                }
            }
        }
    }
}

/**
 * This class defines the widget creation.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class CellSimpleMovieViewHolder(avwView : View) : RecyclerView.ViewHolder(avwView) {
    val mtvTitle            : TextView     = avwView.findViewById(R.id.tvTitle)
    val mtvReleaseDateValue : TextView     = avwView.findViewById(R.id.tvReleaseDateValue)
    val mtvVoteAverageValue : TextView     = avwView.findViewById(R.id.tvVoteAverageValue)
    val mllDetails          : LinearLayout = avwView.findViewById(R.id.llDetails)
    val mivPoster           : ImageView    = avwView.findViewById(R.id.ivPoster)
    val mtvOverView         : TextView     = avwView.findViewById(R.id.tvOverview)
    val mibFavorite         : ImageButton  = avwView.findViewById(R.id.ibFavorite)
    val mbtnExpand          : Button       = avwView.findViewById(R.id.btnExpand)

    init {
        mllDetails.visibility = LinearLayout.GONE
        mibFavorite.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
    }
}

/**
 * This is a simple adapter used to control the R.layou.cell_favorite_movie.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class CellFavoriteMovieAdapter(alstMovies : ArrayList<FavoriteMovie>): androidx.recyclerview.widget.RecyclerView.Adapter<CellFavoriteMovieViewHolder>() {
    var mlstMovies : ArrayList<FavoriteMovie> = alstMovies

    /**
     * This state is necessary to create the viewholder. This
     * object will be used to send new content
     * using adapter notifications.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellFavoriteMovieViewHolder {
        return CellFavoriteMovieViewHolder((LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_favorite_movie, parent, false)))
    }

    /**
     * Return the size number of list objects. This
     * control is used by onBindViewHolder.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun getItemCount(): Int {
        return mlstMovies.size
    }

    /**
     * This method allows to relation the object and viewholder.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onBindViewHolder(holder: CellFavoriteMovieViewHolder, position: Int) {
        val movieTarget = mlstMovies[position]
        holder.mtvTitle.text = movieTarget.title
        holder.mtvReleaseDateValue.text = movieTarget.releaseDate
        holder.mtvVoteAverageValue.text = movieTarget.votesAverage.toString()
    }
}

/**
 * This class defines the widget creation.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class CellFavoriteMovieViewHolder(avwView : View) : RecyclerView.ViewHolder(avwView) {
    val mtvTitle            : TextView = avwView.findViewById(R.id.tvTitle)
    val mtvReleaseDateValue : TextView = avwView.findViewById(R.id.tvReleaseDateValue)
    val mtvVoteAverageValue : TextView = avwView.findViewById(R.id.tvVoteAverageValue)
}