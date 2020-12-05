package com.hendri.movie.catalogue.ui.activities

import android.app.Activity
import android.content.Context
import androidx.navigation.findNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val currentDes =
        { activity: Activity -> activity.findNavController(R.id.nav_host_main_fragment).currentDestination }

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
    fun loadMainActivity() {
        scenarioRule.scenario.onActivity {
            Assert.assertEquals(context.getString(R.string.movie), currentDes(it)?.label)
        }
        Espresso.onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.fragment_movie)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
    }

    @Test
    fun movingBottomNavigation() {
        Espresso.onView(withId(R.id.fragment_tv)).perform(ViewActions.click())
        scenarioRule.scenario.onActivity {
            Assert.assertEquals(context.getString(R.string.tv_show), currentDes(it)?.label)
        }
        Espresso.onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.fragment_tv)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
    }
}