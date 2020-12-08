package com.hendri.movie.catalogue.ui.activities

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.ui.activities.DetailActivity.Companion.DATA_EXTRA
import com.hendri.movie.catalogue.utils.DummyData
import com.hendri.movie.catalogue.utils.DummyDataResponse
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest {
    private lateinit var scenarioRule: ActivityScenario<DetailActivity>
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val dummyMovie = DummyData.getDetailMovie()
    private val dummyTvShow = DummyData.getDetailTvShow()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadDetailMovie() {
        val data = DummyDataResponse.detailMovieResponse()
        scenarioRule = ActivityScenario.launch(
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DATA_EXTRA, arrayListOf(R.id.detail_movie, data.id))
            }
        )
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(withText(data.title)))
        Espresso.onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(withText("Release on : ${data.release_date}")))
        Espresso.onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvOverview)).check(matches(withText(data.overview)))
        Espresso.onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.ivBackground)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.ivBack)).perform(ViewActions.click())
    }

    @Test
    fun loadDetailTvShow() {
        val data = DummyDataResponse.detailTvShowResponse()
        scenarioRule = ActivityScenario.launch(
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DATA_EXTRA, arrayListOf(R.id.detail_tv_show, data.id))
            }
        )
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(withText(data.original_name)))
        Espresso.onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(withText("Release on : ${data.first_air_date}")))
        Espresso.onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvOverview)).check(matches(withText(data.overview)))
        Espresso.onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.ivBackground)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.ivBack)).perform(ViewActions.click())
    }
}