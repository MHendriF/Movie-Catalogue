package com.hendri.movie.catalogue.ui.activities

import android.app.Activity
import android.content.Context
import androidx.navigation.findNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.R.id.*
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val currentDes = { activity: Activity -> activity.findNavController(nav_host_main_fragment).currentDestination }

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        scenarioRule.scenario.onActivity {
            Assert.assertEquals(context.getString(R.string.movie), currentDes(it)?.label)
        }
        onView(withId(bottom_navigation_view)).check(matches(isDisplayed()))
        onView(withId(fragment_movie)).check(matches(isDisplayed()))
        onView(withId(rvMovie)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).perform(click())
        onView(withText(R.string.by_name)).check(matches(isDisplayed()))
        onView(withText(R.string.by_release)).check(matches(isDisplayed()))
        onView(withText(R.string.by_name)).perform(click())
    }

    @Test
    fun loadTvShow() {
        onView(withId(fragment_tv)).perform(click())
        scenarioRule.scenario.onActivity {
            Assert.assertEquals(context.getString(R.string.tv_show), currentDes(it)?.label)
        }
        onView(withId(bottom_navigation_view)).check(matches(isDisplayed()))
        onView(withId(fragment_tv)).check(matches(isDisplayed()))
        onView(withId(rvTvShow)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).perform(click())
        onView(withText(R.string.by_name)).check(matches(isDisplayed()))
        onView(withText(R.string.by_release)).check(matches(isDisplayed()))
        onView(withText(R.string.by_release)).perform(click())
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(fragment_favorite)).perform(click())
        scenarioRule.scenario.onActivity {
            Assert.assertEquals(context.getString(R.string.favorite), currentDes(it)?.label)
        }
        onView(withId(fragment_favorite)).check(matches(isDisplayed()))
        onView(withId(view_pager)).check(matches(isDisplayed()))
        onView(withId(rvMovie)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).perform(click())
        onView(withText(R.string.by_name)).check(matches(isDisplayed()))
        onView(withText(R.string.by_release)).check(matches(isDisplayed()))
        onView(withText(R.string.by_name)).perform(click())
    }

    @Test
    fun loadFavoriteTvShow() {
        onView(withId(fragment_favorite)).perform(click())
        scenarioRule.scenario.onActivity {
            Assert.assertEquals(context.getString(R.string.favorite), currentDes(it)?.label)
        }
        onView(withId(fragment_favorite)).check(matches(isDisplayed()))
        onView(withId(view_pager)).check(matches(isDisplayed()))
        onView(withId(view_pager)).perform(ViewPagerActions.scrollRight())
        onView(withId(rvTvShow)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).check(matches(isDisplayed()))
        onView(withId(menu_sort)).perform(click())
        onView(withText(R.string.by_name)).check(matches(isDisplayed()))
        onView(withText(R.string.by_release)).check(matches(isDisplayed()))
        onView(withText(R.string.by_release)).perform(click())
    }
}