package com.gabrielbmoro.programmingchallenge.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.domain.model.MovieListType
import com.gabrielbmoro.programmingchallenge.presentation.movieList.MovieListFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This is a view that represents the three pages: top rated movies,
 * popular movies, and favorite movies.
 * @author Gabriel Moro
 * @since 2018-08-30
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    private val fragmentsList = listOf(
            MovieListFragment.newInstance(MovieListType.TopRated),
            MovieListFragment.newInstance(MovieListType.Popular),
            MovieListFragment.newInstance(MovieListType.Favorite)
    )
    private lateinit var viewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        vwPagerComponent.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getCount(): Int {
                return fragmentsList.size
            }

            override fun getItem(position: Int): Fragment {
                return fragmentsList[position]
            }
        }

        vwPagerComponent.currentItem = viewModel.getPage()
        vwPagerComponent.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                when (position) {
                    MainViewModel.TOP_RATED_PAGE -> bnvBottomMenu.selectedItemId = R.id.menuTopRatedMovies
                    MainViewModel.POPULAR_PAGE -> bnvBottomMenu.selectedItemId = R.id.menuPopularMovies
                    MainViewModel.FAVORITE_PAGE -> bnvBottomMenu.selectedItemId = R.id.menuFavoriteMovies
                }
                viewModel.setPage(pageIndex = position)
            }
        })

        bnvBottomMenu.setOnNavigationItemSelectedListener {
            if (bnvBottomMenu.selectedItemId == it.itemId) {
                (fragmentsList[vwPagerComponent.currentItem] as? MovieListFragment)?.scrollToTop()
            }

            when (it.itemId) {
                R.id.menuTopRatedMovies -> vwPagerComponent.setCurrentItem(0, false)
                R.id.menuPopularMovies -> vwPagerComponent.setCurrentItem(1, false)
                R.id.menuFavoriteMovies -> vwPagerComponent.setCurrentItem(2, false)
            }
            true
        }

        /**
         * Limit pages in memory.
         */
        vwPagerComponent.offscreenPageLimit = 3
    }

}