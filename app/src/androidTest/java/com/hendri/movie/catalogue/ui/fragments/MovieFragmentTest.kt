package com.hendri.movie.catalogue.ui.fragments

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.SingleNavigationActivity
import com.hendri.movie.catalogue.data.source.remote.response.models.Movie
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_movie.*
import org.junit.*
import org.junit.Assert.*

class MovieFragmentTest {
    @get:Rule
    val scenarioRule = ActivityScenarioRule(SingleNavigationActivity::class.java)

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
        scenarioRule.scenario.onActivity { it.startDestination(R.navigation.nav_graph_home, R.id.fragment_movie) }
        Espresso.onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        val data = mutableListOf<Movie>()
        scenarioRule.scenario.onActivity {
            data.addAll((it.rvMovie.adapter as MovieAdapter).data)
        }
        assertNotNull(data)
        assertTrue(data.size > 0)
        Espresso.onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(data.size))
        Espresso.onView(withId(R.id.rvMovie)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.tvReadMore)).perform(click())
        Espresso.onView(withId(R.id.ivBack)).perform(click())
    }
}