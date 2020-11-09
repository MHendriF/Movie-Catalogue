package com.hendri.movie.catalogue.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.utils.DataDummy
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun loadMovie() {
        Espresso.onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(withText(dummyMovie[0].title)))
        Espresso.onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDescription)).check(matches(withText(dummyMovie[0].description)))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(withText("Release on : ${dummyMovie[0].releaseDate}")))
        Espresso.onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvGenre)).check(matches(withText(dummyMovie[0].genre)))
        Espresso.onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvScore)).check(matches(withText("${dummyMovie[0].score}%")))
        Espresso.onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.ivBackground)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.ivBack)).perform(ViewActions.click())
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(withText("TV Show")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvTitle)).check(matches(withText(dummyTvShow[0].title)))
        Espresso.onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvDescription)).check(matches(withText(dummyTvShow[0].description)))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReleaseDate)).check(matches(withText("Release on : ${dummyTvShow[0].releaseDate}")))
        Espresso.onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvGenre)).check(matches(withText(dummyTvShow[0].genre)))
        Espresso.onView(withId(R.id.tvScore)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvScore)).check(matches(withText("${dummyTvShow[0].score}%")))
        Espresso.onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.ivBackground)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.ivBack)).perform(ViewActions.click())
    }
}