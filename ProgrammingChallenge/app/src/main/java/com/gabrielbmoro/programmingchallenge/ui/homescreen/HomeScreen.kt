package com.gabrielbmoro.programmingchallenge.ui.homescreen

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.ui.homescreen.pages.FavoriteMoviesFragment
import com.gabrielbmoro.programmingchallenge.ui.homescreen.pages.PopularMoviesFragment
import com.gabrielbmoro.programmingchallenge.ui.homescreen.pages.TopRatedFragment
import me.relex.circleindicator.CircleIndicator

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
     * Presenter defines the load movies action.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    interface Presenter { }
    /**
     * View defines the setup and update screen operations.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    interface View{
        fun loadPages()
    }
}

/**
 * This is a view that represents the three pages: top rated movies,
 * popular movies, and favorite movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class HomeScreenActivity : AppCompatActivity(), HomeScreenContract.View {

    private var presenter    : HomeScreenContract.Presenter? = null
    private var mvwViewPager : ViewPager?                    = null
    private var mciIndicator : CircleIndicator?              = null

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
}

class HomeScreenPresenter(aview : HomeScreenContract.View) : HomeScreenContract.Presenter {
    private val view : HomeScreenContract.View = aview
    // object
}