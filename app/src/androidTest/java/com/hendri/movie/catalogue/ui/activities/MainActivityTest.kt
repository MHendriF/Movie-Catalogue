package com.hendri.movie.catalogue.ui.activities

import android.app.Activity
import android.content.Context
import androidx.navigation.findNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.junit.*

class MainActivityTest {
    private val ctx = ApplicationProvider.getApplicationContext<Context>()
    private val currentDes =
        { activity: Activity -> activity.findNavController(R.id.nav_host_main_fragment).currentDestination }

    @get:Rule
    val asr = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun main_activity_loaded() {
        asr.scenario.onActivity {
            Assert.assertEquals(ctx.getString(R.string.movie), currentDes(it)?.label)
        }

        Espresso.onView(withId(R.id.bottom_navigation_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fragment_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun bottom_navigation_move_to_tv() {
        Espresso.onView(withId(R.id.fragment_tv)).perform(ViewActions.click())
        asr.scenario.onActivity {
            Assert.assertEquals(ctx.getString(R.string.tv_show), currentDes(it)?.label)
        }
        Espresso.onView(withId(R.id.fragment_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}