package com.gabrielbmoro.programmingchallenge.ui.homescreen

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.network_monitor.ConnectivityReceiver
import com.gabrielbmoro.programmingchallenge.ui.homescreen.pages.FavoriteMoviesFragment
import com.gabrielbmoro.programmingchallenge.ui.homescreen.pages.PopularMoviesFragment
import com.gabrielbmoro.programmingchallenge.ui.homescreen.pages.TopRatedFragment
import me.relex.circleindicator.CircleIndicator
import android.support.design.widget.Snackbar

/**
 * This contract provides two interfaces:
 * - Presenter is used to manipulate the app's business
 * objects;
 * - View is used to control the widgets components.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
interface HomeScreenContract {
    /**
     * View defines the setup and update screen operations.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    interface View{
        fun loadPages()
        fun showSnackBar(astrMessage : String)
        fun hideSnackBar()
    }
}

/**
 * This is a view that represents the three pages: top rated movies,
 * popular movies, and favorite movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class HomeScreenActivity : AppCompatActivity(), HomeScreenContract.View, ConnectivityReceiver.ConnectivityReceiverListener  {

    private var mvwViewPager : ViewPager?                    = null
    private var mciIndicator : CircleIndicator?              = null
    private var msbSnackBar  : Snackbar?                     = null

    /**
     * This method is called before screen creation.
     * I use it to get the widgets components.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        mvwViewPager = findViewById(R.id.vwPagerComponent)
        mciIndicator = findViewById(R.id.indicator)

        loadPages()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(abisConnected: Boolean) {
        if(!abisConnected)
            showSnackBar(resources.getString(R.string.offlinenow))
        else
            hideSnackBar()
    }

    /**
     * This method starts the viewpager component, this
     * component shows the three pages like a book.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun loadPages() {
        val lstPages = ArrayList<Fragment>()
        lstPages.add(TopRatedFragment())
        lstPages.add(PopularMoviesFragment())
        lstPages.add(FavoriteMoviesFragment())
        mvwViewPager?.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return lstPages.size
            }

            override fun getItem(position: Int): Fragment {
                return lstPages[position]
            }
        }
        mciIndicator?.setViewPager(mvwViewPager)
        mvwViewPager?.adapter?.registerDataSetObserver(mciIndicator?.dataSetObserver)
    }

    /**
     * This method shows the snackbar with message
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun showSnackBar(astrMessage: String) {
        msbSnackBar = Snackbar.make(findViewById(R.id.clParentView), astrMessage, Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
        msbSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
        msbSnackBar?.show()
    }


    /**
     * Hide the snackbar component.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun hideSnackBar() {
        msbSnackBar?.dismiss()
    }
}