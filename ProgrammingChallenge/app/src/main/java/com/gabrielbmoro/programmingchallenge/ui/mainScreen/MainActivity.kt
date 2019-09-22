package com.gabrielbmoro.programmingchallenge.ui.mainScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.databinding.ActivityMainBinding
import com.gabrielbmoro.programmingchallenge.ui.mainScreen.page.MovieListFragment

/**
 * This is a view that represents the three pages: top rated movies,
 * popular movies, and favorite movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        loadPages()
    }

    private fun loadPages() {
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

        binding.vwPagerComponent.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.bnvBottomMenu.selectedItemId = R.id.menuTopRatedMovies
                    1 -> binding.bnvBottomMenu.selectedItemId = R.id.menuPopularMovies
                    2 -> binding.bnvBottomMenu.selectedItemId = R.id.menuFavoriteMovies
                }
            }
        })

        binding.bnvBottomMenu.setOnNavigationItemSelectedListener {


            if (binding.bnvBottomMenu.selectedItemId == it.itemId) {
                (lstPages[binding.vwPagerComponent.currentItem] as? MovieListFragment)?.scrollToTop()
            }

            when (it.itemId) {
                R.id.menuTopRatedMovies -> binding.vwPagerComponent.setCurrentItem(0, false)
                R.id.menuPopularMovies -> binding.vwPagerComponent.setCurrentItem(1, false)
                R.id.menuFavoriteMovies -> binding.vwPagerComponent.setCurrentItem(2, false)
            }
            true
        }

        /**
         * Limit pages in memory.
         */
        binding.vwPagerComponent.offscreenPageLimit = 3
    }
}