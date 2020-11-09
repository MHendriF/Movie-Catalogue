package com.hendri.movie.catalogue.ui.viewmodels

import com.hendri.movie.catalogue.utils.Constants.TYPE_MOVIE
import com.hendri.movie.catalogue.utils.Constants.TYPE_TVSHOW
import com.hendri.movie.catalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getDataMovieById() {
        val movie = viewModel.getDataById(movieId, TYPE_MOVIE)
        assertNotNull(movie)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.description, movie.description)
        assertEquals(dummyMovie.genre, movie.genre)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.score, movie.score)
        assertEquals(dummyMovie.imgPoster, movie.imgPoster)
        assertEquals(dummyMovie.imgBackground, movie.imgBackground)
    }

    @Test
    fun getDataTvShowById() {
        val tvShow = viewModel.getDataById(tvShowId, TYPE_TVSHOW)
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.description, tvShow.description)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.releaseDate, tvShow.releaseDate)
        assertEquals(dummyTvShow.score, tvShow.score)
        assertEquals(dummyTvShow.imgPoster, tvShow.imgPoster)
        assertEquals(dummyTvShow.imgBackground, tvShow.imgBackground)
    }
}