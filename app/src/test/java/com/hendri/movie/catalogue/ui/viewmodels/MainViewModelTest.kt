package com.hendri.movie.catalogue.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.Resource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSourceTest.Companion.errorMessage
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.DummyDataResponse
import com.hendri.movie.catalogue.utils.LiveDataTestUtil.getValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private val dummyDataMovie = DummyDataResponse.movieResponse()
    private val dummyDataTvShow = DummyDataResponse.tvShowResponse()

    @Mock
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var observerResourceMovie: Observer<Resource<MovieResponse>>

    @Mock
    lateinit var observerResourceTvShow: Observer<Resource<TvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun get_movie_resource_success() {
        Mockito.`when`(mainRepository.getMovies()).thenReturn(MutableLiveData(Resource.Success(dummyDataMovie)))

        val resource = getValue(mainViewModel.getMovies)
        Mockito.verify(mainRepository).getMovies()

        mainViewModel.getMovies.observeForever(observerResourceMovie)
        Mockito.verify(observerResourceMovie).onChanged(Resource.Success(dummyDataMovie))

        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyDataMovie, resource.data)
            }
        }
    }

    @Test
    fun get_movie_resource_error() {
        Mockito.`when`(mainRepository.getMovies()).thenReturn(MutableLiveData(Resource.Error(errorMessage)))

        val resource = getValue(mainViewModel.getMovies)
        Mockito.verify(mainRepository).getMovies()

        mainViewModel.getMovies.observeForever(observerResourceMovie)
        Mockito.verify(observerResourceMovie).onChanged(Resource.Error(errorMessage))

        assertTrue(resource is Resource.Error)
        when (resource) {
            is Resource.Error -> {
                assertEquals(errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun get_movie_resource_empty() {
        Mockito.`when`(mainRepository.getMovies()).thenReturn(MutableLiveData(Resource.Empty(null)))

        val resource = getValue(mainViewModel.getMovies)
        Mockito.verify(mainRepository).getMovies()

        mainViewModel.getMovies.observeForever(observerResourceMovie)
        Mockito.verify(observerResourceMovie).onChanged(Resource.Empty(null))

        assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                assertNull(resource.data)
            }
        }
    }

    @Test
    fun get_tv_show_resource_success() {
        Mockito.`when`(mainRepository.getTvShows()).thenReturn(MutableLiveData(Resource.Success(dummyDataTvShow)))

        val resource = getValue(mainViewModel.getTvShows)
        Mockito.verify(mainRepository).getTvShows()

        mainViewModel.getTvShows.observeForever(observerResourceTvShow)
        Mockito.verify(observerResourceTvShow).onChanged(Resource.Success(dummyDataTvShow))

        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyDataTvShow, resource.data)
            }
        }
    }

    @Test
    fun get_tv_show_resource_error() {
        Mockito.`when`(mainRepository.getTvShows())
            .thenReturn(MutableLiveData(Resource.Error(errorMessage)))

        val resource = getValue(mainViewModel.getTvShows)
        Mockito.verify(mainRepository).getTvShows()

        mainViewModel.getTvShows.observeForever(observerResourceTvShow)
        Mockito.verify(observerResourceTvShow).onChanged(Resource.Error(errorMessage))

        assertTrue(resource is Resource.Error)
        when (resource) {
            is Resource.Error -> {
                assertEquals(errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun get_tv_show_resource_empty() {
        Mockito.`when`(mainRepository.getTvShows())
            .thenReturn(MutableLiveData(Resource.Empty(null)))

        val resource = getValue(mainViewModel.getTvShows)
        Mockito.verify(mainRepository).getTvShows()

        mainViewModel.getTvShows.observeForever(observerResourceTvShow)
        Mockito.verify(observerResourceTvShow).onChanged(Resource.Empty(null))

        assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                assertNull(resource.data)
            }
        }
    }
}