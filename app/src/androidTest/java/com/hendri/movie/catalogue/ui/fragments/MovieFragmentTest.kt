package com.hendri.movie.catalogue.ui.fragments

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.SingleNavigationActivity
import com.hendri.movie.catalogue.data.source.remote.response.the_movie_db.Movie
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_movie.*
import org.junit.*
import kotlin.random.Random

class MovieFragmentTest {
    @get:Rule
    val asr = ActivityScenarioRule(SingleNavigationActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun fragment_loaded() {
        asr.scenario
            .onActivity { it.startDestination(R.navigation.nav_graph_home, R.id.fragment_movie) }
        Espresso.onView(withId(R.id.rvMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val data = mutableListOf<Movie>()
        asr.scenario.onActivity {
            data.addAll((it.rvMovie.adapter as MovieAdapter).data)
        }

        Assert.assertTrue(data.size > 0)
        Espresso.onView(withId(R.id.rvMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(data.size - 1))

        Espresso.onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                Random.nextInt(
                    0,
                    data.size - 1
                ), ViewActions.click()
            )
        )
    }
}