package com.hendri.movie.catalogue.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.data.api.ApiHelper
import com.hendri.movie.catalogue.data.local.DatabaseHelper
import com.hendri.movie.catalogue.data.repository.MainRepository
import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.ui.viewmodels.MovieViewModel
import com.hendri.movie.catalogue.utils.Resource
import com.hendri.movie.catalogue.utils.TestCoroutineRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieFragmentTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var databaseHelper: DatabaseHelper

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var apiMoviesObserver: Observer<Resource<List<Movie>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun get_movies_response_success() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Movie>())
                .`when`(apiHelper)
                .getMovies()
            val viewModel = MovieViewModel(repository)
            viewModel.getMovies().observeForever(apiMoviesObserver)
            verify(apiHelper).getMovies()
            verify(apiMoviesObserver).onChanged(Resource.success(emptyList()))
            viewModel.getMovies().removeObserver(apiMoviesObserver)

        }
    }

    @Test
    fun get_movies_response_error() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getMovies()
            val viewModel = MovieViewModel(repository)
            viewModel.getMovies().observeForever(apiMoviesObserver)
            verify(apiHelper).getMovies()
            verify(apiMoviesObserver).onChanged(
                Resource.error(
                    null,
                    RuntimeException(errorMessage).toString()
                )
            )
            viewModel.getMovies().removeObserver(apiMoviesObserver)

        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}