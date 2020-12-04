package com.hendri.movie.catalogue.ui.activities

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.ui.activities.DetailActivity.Companion.DATA_EXTRA
import com.hendri.movie.catalogue.utils.DummyDataResponse
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailActivityTest {
    private lateinit var scenario: ActivityScenario<DetailActivity>
    private val ctx = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun detailMovieLoaded() {
        val data = DummyDataResponse.movieDetailResponse()
        scenario = ActivityScenario.launch(
            Intent(ctx, DetailActivity::class.java).apply {
                putExtra(DATA_EXTRA, arrayListOf(R.id.detail_movie, data.id))
            }
        )

        Espresso.onView(withText(data.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withText(data.title))
            .check(ViewAssertions.matches(withId(R.id.tvTitle)))
        Espresso.onView(withText(data.overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withText(data.overview))
            .check(ViewAssertions.matches(withId(R.id.tvOverview)))
    }

    @Test
    fun detailTvLoaded() {
        val data = DummyDataResponse.tvDetailResponse()
        scenario = ActivityScenario.launch(
            Intent(ctx, DetailActivity::class.java).apply {
                putExtra(DATA_EXTRA, arrayListOf(R.id.detail_tv, data.id))
            }
        )

        Espresso.onView(withText(data.original_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withText(data.original_name))
            .check(ViewAssertions.matches(withId(R.id.tvTitle)))
        Espresso.onView(withText(data.overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withText(data.overview))
            .check(ViewAssertions.matches(withId(R.id.tvOverview)))
    }
}