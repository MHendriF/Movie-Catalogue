package com.hendri.movie.catalogue.ui.viewmodels

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ContentViewModelTest {
    private lateinit var viewModel: ContentViewModel

    @Before
    fun setUp() {
        viewModel = ContentViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertTrue(movies.size >= 10)
    }

    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()
        assertNotNull(tvShows)
        assertTrue(tvShows.size >= 10)
    }
}