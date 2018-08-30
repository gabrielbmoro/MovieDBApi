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

interface HomeScreenContract {
    interface Presenter {

    }
    interface View{
        fun loadPages()
    }
}

class HomeScreenActivity : AppCompatActivity(), HomeScreenContract.View {


    private var presenter : HomeScreenContract.Presenter? = null
    private var mvwViewPager : ViewPager? = null
    private var mciIndicator : CircleIndicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        mvwViewPager = findViewById(R.id.vwPagerComponent)
        mciIndicator = findViewById(R.id.indicator)

        loadPages()
    }

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

/*

  class ViewPagerAdapter(a_fmFragmentManager : FragmentManager, a_lstPages : ArrayList<Fragment>) : FragmentPagerAdapter(a_fmFragmentManager) {

    private val mlstPages : ArrayList<Fragment> = a_lstPages

    override fun getItem(position: Int): Fragment {
        return mlstPages[position]
    }

    override fun getCount(): Int {
        return mlstPages.size
    }

}


  private fun loadViewPageAdapter() {
        val lstPages = ArrayList<Fragment>()
        lstPages.add(FragmentExpensesList())
        lstPages.add(FragmentFinancialReport())
        lstPages.add(FragmentSalaryList())

        mvwViewPager?.adapter = ViewPagerAdapter(supportFragmentManager, lstPages)
        mvwViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                mPrevMenuItem?.isChecked = false

                val mTargetMenu: MenuItem? = when {
                    position == 0 -> mnvNavigation?.menu?.findItem(R.id.navigation_expenses)
                    position == 1 -> mnvNavigation?.menu?.findItem(R.id.navigation_piechart)
                    position == 2 -> mnvNavigation?.menu?.findItem(R.id.navigation_salary)
                    else -> null
                }
                mTargetMenu?.isChecked = true
                mPrevMenuItem = mTargetMenu
            }
        })
    }
*/

class HomeScreenPresenter(aview : HomeScreenContract.View) : HomeScreenContract.Presenter {
    private val view : HomeScreenContract.View = aview
    // object
}