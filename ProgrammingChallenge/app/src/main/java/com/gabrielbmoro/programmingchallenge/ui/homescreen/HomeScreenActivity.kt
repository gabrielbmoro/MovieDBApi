package com.gabrielbmoro.programmingchallenge.ui.homescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.databinding.ActivityHomescreenBinding
import com.gabrielbmoro.programmingchallenge.ui.homescreen.page.MovieListFragment
import com.google.android.material.snackbar.Snackbar

/**
 * This is a view that represents the three pages: top rated movies,
 * popular movies, and favorite movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class HomeScreenActivity : AppCompatActivity(), HomeScreenContract.View {

    private lateinit var binding: ActivityHomescreenBinding
    private var snackBar: Snackbar? = null

    /**
     * This method is called before screen creation.
     * I use it to get the widgets components.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homescreen)

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
        lstPages.add(MovieListFragment.newInstance(MovieListFragment.TOP_RATED_MOVIES_TYPE))
        lstPages.add(MovieListFragment.newInstance(MovieListFragment.POPULAR_MOVIES_TYPE))
        lstPages.add(MovieListFragment.newInstance(MovieListFragment.FAVORITE_MOVIES_TYPE))

        binding.vwPagerComponent.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int {
                return lstPages.size
            }

            override fun getItem(position: Int): Fragment {
                return lstPages[position]
            }
        }

        binding.bnvBottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menuTopRatedMovies -> binding.vwPagerComponent.setCurrentItem(0, false)
                R.id.menuPopularMovies -> binding.vwPagerComponent.setCurrentItem(1, false)
                R.id.menuFavoriteMovies -> binding.vwPagerComponent.setCurrentItem(2, false)
            }
            true
        }

        /**
         * Limit pages in memory.
         */
        binding.vwPagerComponent.offscreenPageLimit = 0
    }

    /**
     * This method shows the snackbar with message
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun showSnackBar(astrMessage: String) {
        snackBar = Snackbar.make(findViewById(R.id.clParentView), astrMessage, Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
        snackBar?.show()
    }


    /**
     * Hide the snackbar component.
     * @author Gabriel Moro
     * @since 2018-08-30
     */
    override fun hideSnackBar() {
        snackBar?.dismiss()
    }
}