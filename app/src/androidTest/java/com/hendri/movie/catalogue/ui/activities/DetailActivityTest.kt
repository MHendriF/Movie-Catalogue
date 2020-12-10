package com.hendri.movie.catalogue.ui.activities

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.ui.activities.DetailActivity.Companion.DATA_EXTRA
import com.hendri.movie.catalogue.utils.DummyData
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest {
    private lateinit var scenarioRule: ActivityScenario<DetailActivity>
    private val context = ApplicationProvider.getApplicationContext<Context>()

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
        val data = DummyData.getDetailMovie()
        scenarioRule = ActivityScenario.launch(
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DATA_EXTRA, arrayListOf(R.id.detail_movie, data.id))
            }
        )
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(data.title)))
        onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(withText("Release on : ${data.release_date}")))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(data.overview)))
        onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.ivBackground)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReadMore)).perform(click())
        onView(withId(R.id.ivFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.ivFavorite)).perform(click())
        onView(withText(R.string.ok)).check(matches(withText("OK")))
        onView(withText(R.string.cancel)).check(matches(withText("Cancel")))
        onView(withText("Cancel")).perform(click())
        onView(withId(R.id.ivBack)).perform(click())
    }

    @Test
    fun loadDetailTvShow() {
        val data = DummyData.getDetailTvShow()
        scenarioRule = ActivityScenario.launch(
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DATA_EXTRA, arrayListOf(R.id.detail_tv_show, data.id))
            }
        )
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(data.original_name)))
        onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(withText("Release on : ${data.first_air_date}")))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(data.overview)))
        onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.ivBackground)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReadMore)).perform(click())
        onView(withId(R.id.ivFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.ivFavorite)).perform(click())
        onView(withText(R.string.ok)).check(matches(withText("OK")))
        onView(withText(R.string.cancel)).check(matches(withText("Cancel")))
        onView(withText("Cancel")).perform(click())
        onView(withId(R.id.ivBack)).perform(click())
    }
}