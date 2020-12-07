package com.hendri.movie.catalogue.ui.fragments

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.SingleNavigationActivity
import com.hendri.movie.catalogue.data.source.remote.response.models.TvShow
import com.hendri.movie.catalogue.ui.adapters.TvShowAdapter
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {
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
    fun loadFragmentTvShow() {
        scenarioRule.scenario.onActivity {
            it.startDestination(R.navigation.nav_graph_home, R.id.fragment_tv)
        }
        Espresso.onView(withId(R.id.rvTvShow)).check(matches(ViewMatchers.isDisplayed()))
        val data = mutableListOf<TvShow>()
        scenarioRule.scenario.onActivity {
            data.addAll((it.rvTvShow.adapter as TvShowAdapter).data)
        }
        assertNotNull(data)
        assertTrue(data.size > 0)
        Espresso.onView(withId(R.id.rvTvShow)).perform(scrollToPosition<ViewHolder>(data.size))
        Espresso.onView(withId(R.id.rvTvShow)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.tvReadMore)).perform(click())
        Espresso.onView(withId(R.id.ivBack)).perform(click())
    }
}