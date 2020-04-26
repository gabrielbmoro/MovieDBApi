import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.viewpager.widget.ViewPager
import com.gabrielbmoro.programmingchallenge.R
import com.gabrielbmoro.programmingchallenge.presentation.MainActivity
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
@LargeTest
class SelectTabBehaviorTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun whenIsTheFirstTabTryToGoToThePreviousTab() {
        onView(withId(R.id.vwPagerComponent))
                .perform(ViewActions.swipeRight())
                .check { view, _ ->
                    (view as? ViewPager)?.let {
                        assertThat(it.currentItem).isEqualTo(FIRST_TAB)
                    }
                }
    }

    @Test
    fun whenIsTheFirstTabTryToGoToTheNextTab() {
        onView(
                withId(R.id.vwPagerComponent)
        ).perform(ViewActions.swipeLeft())
                .check { view, _ ->
                    (view as? ViewPager)?.let {
                        assertThat(it.currentItem).isEqualTo(SECOND_TAB)
                    }
                }
    }

    @Test
    fun whenIsTheSecondTabTryToGoToThePreviousTab() {
        onView(withId(R.id.vwPagerComponent))
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeRight())
                .check { view, _ ->
                    (view as? ViewPager)?.let {
                        assertThat(it.currentItem).isEqualTo(FIRST_TAB)
                    }
                }
    }

    @Test
    fun whenIsTheSecondTabTryToGoToTheNextTab() {
        onView(withId(R.id.vwPagerComponent))
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeLeft())
                .check { view, _ ->
                    (view as? ViewPager)?.let {
                        assertThat(it.currentItem).isEqualTo(THIRD_TAB)
                    }
                }
    }

    @Test
    fun whenIsTheThirdTabTryToGoToThePreviousTab() {
        onView(withId(R.id.vwPagerComponent))
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeRight())
                .check { view, _ ->
                    (view as? ViewPager)?.let {
                        assertThat(it.currentItem).isEqualTo(SECOND_TAB)
                    }
                }
    }

    @Test
    fun whenIsTheThirdTabTryToGoToTheNextTab() {
        onView(withId(R.id.vwPagerComponent))
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeLeft())
                .check { view, _ ->
                    (view as? ViewPager)?.let {
                        assertThat(it.currentItem).isEqualTo(THIRD_TAB)
                    }
                }
    }

    @Test
    fun whenIsTheThirdTabTryToGoToTheFirstTab() {
        onView(withId(R.id.vwPagerComponent))
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeLeft())
                .perform(ViewActions.swipeRight())
                .perform(ViewActions.swipeRight())
                .check { view, _ ->
                    (view as? ViewPager)?.let {
                        assertThat(it.currentItem).isEqualTo(FIRST_TAB)
                    }
                }
    }

    companion object{
        const val FIRST_TAB = 0
        const val SECOND_TAB = 1
        const val THIRD_TAB = 2
    }

}