package com.hendri.movie.catalogue.ui.fragments

import android.app.Activity
import android.content.Context
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.ui.activities.MainActivity
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val currentDes = { activity: Activity -> activity.findNavController(R.id.nav_host_main_fragment).currentDestination }

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
    fun loadFragmentMovie() {
        val data = mutableListOf<Movie>()

        scenarioRule.scenario.onActivity {
            assertEquals(context.getString(R.string.movie), currentDes(it)?.label)
        }
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        scenarioRule.scenario.onActivity { activity ->
            val rvMovie: RecyclerView = activity.findViewById(R.id.rvMovie)
            (rvMovie.adapter as MovieAdapter).currentList?.map { data.add(it) }
        }
        assertNotNull(data)
        assertTrue(data.size > 0)
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(data.size))
        onView(withId(R.id.rvMovie)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.tvReadMore)).perform(click())
        onView(withId(R.id.ivBack)).perform(click())
    }
}