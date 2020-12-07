package com.hendri.movie.catalogue.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hendri.movie.catalogue.data.Resource
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.data.repository.MovieRepository
import com.hendri.movie.catalogue.data.repository.TvShowRepository
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSourceTest.Companion.errorMessage
import com.hendri.movie.catalogue.utils.DummyData.movie
import com.hendri.movie.catalogue.utils.DummyData.tvShow
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
    private val tvResourceSuccess =
        Resource.Success(PagedListTestUtil.mockPagedList(tvShow()))
    private val movieResourceSuccess =
        Resource.Success(PagedListTestUtil.mockPagedList(movie()))
    private val resourceError = Error("error", null)
    private val resourceEmpty = Resource.Empty(null)


    @Mock
    lateinit var movieRepo: MovieRepository

    @Mock
    lateinit var tvShowRepo: TvShowRepository

    @Mock
    lateinit var observerMovie: Observer<Resource<PagedList<Movie>>>

    @Mock
    lateinit var observerTvShow: Observer<Resource<PagedList<TvShow>>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(movieRepo, tvShowRepo)
    }

    @Test
    fun getResourceMovieSuccess() {
        Mockito.`when`(movieRepo.getResult()).thenReturn(MutableLiveData(Resource.Success(movieResourceSuccess)))
        val resource = getValue(mainViewModel.movie)
        Mockito.verify(movieRepo).getResult()
        mainViewModel.movie.observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(Resource.Success(movieResourceSuccess))
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(movieResourceSuccess, resource.data)
            }
        }
    }

    @Test
    fun getResourceMovieError() {
        Mockito.`when`(movieRepo.getResult()).thenReturn(MutableLiveData(Resource.Error(errorMessage)))
        val resource = getValue(mainViewModel.movie)
        Mockito.verify(movieRepo).getResult()
        mainViewModel.movie.observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(Resource.Error(errorMessage))
        assertTrue(resource is Resource.Error)
        when (resource) {
            is Resource.Error -> {
                assertEquals(errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun getResourceMovieEmpty() {
        Mockito.`when`(movieRepo.getResult()).thenReturn(MutableLiveData(Resource.Empty(null)))
        val resource = getValue(mainViewModel.movie)
        Mockito.verify(movieRepo).getResult()
        mainViewModel.movie.observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(Resource.Empty(null))
        assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                assertNull(resource.data)
            }
        }
    }

    @Test
    fun getResourceTvShowSuccess() {
        Mockito.`when`(mainRepository.getTvShows()).thenReturn(MutableLiveData(Resource.Success(dummyDataTvShow)))
        val resource = getValue(mainViewModel.getTvShows)
        Mockito.verify(mainRepository).getTvShows()
        mainViewModel.getTvShows.observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(Resource.Success(dummyDataTvShow))
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyDataTvShow, resource.data)
            }
        }
    }

    @Test
    fun getResourceTvShowError() {
        Mockito.`when`(mainRepository.getTvShows()).thenReturn(MutableLiveData(Resource.Error(errorMessage)))
        val resource = getValue(mainViewModel.getTvShows)
        Mockito.verify(mainRepository).getTvShows()
        mainViewModel.getTvShows.observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(Resource.Error(errorMessage))
        assertTrue(resource is Resource.Error)
        when (resource) {
            is Resource.Error -> {
                assertEquals(errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun getResourceTvShowEmpty() {
        Mockito.`when`(mainRepository.getTvShows()).thenReturn(MutableLiveData(Resource.Empty(null)))
        val resource = getValue(mainViewModel.getTvShows)
        Mockito.verify(mainRepository).getTvShows()
        mainViewModel.getTvShows.observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(Resource.Empty(null))
        assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                assertNull(resource.data)
            }
        }
    }
}