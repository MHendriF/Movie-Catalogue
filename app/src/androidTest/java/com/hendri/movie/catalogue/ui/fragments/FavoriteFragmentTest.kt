package com.hendri.movie.catalogue.ui.fragments

import android.app.Activity
import android.content.Context
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.R.id.*
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.ui.activities.MainActivity
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.ui.adapters.TvShowAdapter
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteFragmentTest {
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
    fun loadFragmentFavoriteMovie() {
        val data = mutableListOf<Movie>()
        onView(withId(fragment_favorite)).perform(click())
        scenarioRule.scenario.onActivity {
            assertEquals(context.getString(R.string.favorite), currentDes(it)?.label)
        }
        onView(withId(fragment_favorite)).check(matches(isDisplayed()))
        onView(withId(view_pager)).check(matches(isDisplayed()))
        onView(withId(rvMovie)).check(matches(isDisplayed()))
        scenarioRule.scenario.onActivity { activity ->
            val rvMovie: RecyclerView = activity.findViewById(R.id.rvMovie)
            (rvMovie.adapter as MovieAdapter).currentList?.map { data.add(it) }
        }
        if (data.size > 0) {
            assertNotNull(data)
            assertTrue(data.size > 0)
            onView(withId(rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(data.size))
            onView(withId(rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(tvReadMore)).perform(click())
            onView(withId(ivBack)).perform(click())
        }
    }

    @Test
    fun loadFragmentFavoriteTvShow() {
        val data = mutableListOf<TvShow>()
        onView(withId(fragment_favorite)).perform(click())
        scenarioRule.scenario.onActivity {
            assertEquals(context.getString(R.string.favorite), currentDes(it)?.label)
        }
        onView(withId(fragment_favorite)).check(matches(isDisplayed()))
        onView(withId(view_pager)).check(matches(isDisplayed()))
        onView(withId(view_pager)).perform(ViewPagerActions.scrollRight())
        onView(withId(rvTvShow)).check(matches(isDisplayed()))
        scenarioRule.scenario.onActivity { activity ->
            val rvTvShow: RecyclerView = activity.findViewById(R.id.rvTvShow)
            (rvTvShow.adapter as TvShowAdapter).currentList?.map { data.add(it) }
        }
        if (data.size > 0) {
            assertNotNull(data)
            assertTrue(data.size > 0)
            onView(withId(rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(data.size))
            onView(withId(rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(tvReadMore)).perform(click())
            onView(withId(ivBack)).perform(click())
        }
    }
}