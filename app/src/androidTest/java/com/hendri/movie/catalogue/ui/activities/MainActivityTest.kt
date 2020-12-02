package com.hendri.movie.catalogue.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.hendri.movie.catalogue.R
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {


    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadMovie() {

    }

    @Test
    fun loadDetailMovie() {

    }

    @Test
    fun loadTvShow() {

    }

    @Test
    fun loadDetailTvShow() {

    }
}