package com.hendri.movie.catalogue.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.vo.Resource.*
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.data.repository.MovieRepository
import com.hendri.movie.catalogue.data.repository.TvShowRepository
import com.hendri.movie.catalogue.utils.DummyData.getMovie
import com.hendri.movie.catalogue.utils.DummyData.getTvShow
import com.hendri.movie.catalogue.utils.PagedListTestUtil
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
    private val movieResourceSuccess = Success(PagedListTestUtil.mockPagedList(getMovie()))
    private val tvShowResourceSuccess = Success(PagedListTestUtil.mockPagedList(getTvShow()))
    private val resourceError = Error("error", null)
    private val resourceEmpty = Empty(null)

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
        Mockito.`when`(movieRepo.getResult()).thenReturn(MutableLiveData(movieResourceSuccess))
        Mockito.verify(movieRepo).getResult()

        MutableLiveData(movieResourceSuccess).observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(movieResourceSuccess)

        assertEquals(getMovie(), movieResourceSuccess.data.snapshot())
    }

    @Test
    fun getResourceMovieError() {
        Mockito.`when`(movieRepo.getResult()).thenReturn(MutableLiveData(resourceError))
        Mockito.verify(movieRepo).getResult()

        MutableLiveData(resourceError).observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(resourceError)
    }

    @Test
    fun getResourceMovieEmpty() {
        Mockito.`when`(movieRepo.getResult()).thenReturn(MutableLiveData(resourceEmpty))
        Mockito.verify(movieRepo).getResult()

        MutableLiveData(resourceEmpty).observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(resourceEmpty)
    }

    @Test
    fun getResourceTvShowSuccess() {
        Mockito.`when`(tvShowRepo.getResult()).thenReturn(MutableLiveData(tvShowResourceSuccess))
        Mockito.verify(tvShowRepo).getResult()

        MutableLiveData(tvShowResourceSuccess).observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(tvShowResourceSuccess)

        assertEquals(getTvShow(), tvShowResourceSuccess.data.snapshot())
    }

    @Test
    fun getResourceTvShowError() {
        Mockito.`when`(tvShowRepo.getResult()).thenReturn(MutableLiveData(resourceError))
        Mockito.verify(tvShowRepo).getResult()

        MutableLiveData(resourceError).observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(resourceError)
    }

    @Test
    fun getResourceTvShowEmpty() {
        Mockito.`when`(tvShowRepo.getResult()).thenReturn(MutableLiveData(resourceEmpty))
        Mockito.verify(tvShowRepo).getResult()

        MutableLiveData(resourceEmpty).observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(resourceEmpty)
    }

    @Test
    fun getResourceFavoriteMovieSuccess() {
        Mockito.`when`(movieRepo.getFavorite()).thenReturn(MutableLiveData(movieResourceSuccess))
        Mockito.verify(movieRepo).getFavorite()

        MutableLiveData(movieResourceSuccess).observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(movieResourceSuccess)

        assertEquals(getMovie(), movieResourceSuccess.data.snapshot())
    }

    @Test
    fun getResourceFavoriteMovieError() {
        Mockito.`when`(movieRepo.getFavorite()).thenReturn(MutableLiveData(resourceError))
        Mockito.verify(movieRepo).getFavorite()

        MutableLiveData(resourceError).observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(resourceError)
    }

    @Test
    fun getResourceFavoriteMovieEmpty() {
        Mockito.`when`(movieRepo.getFavorite()).thenReturn(MutableLiveData(resourceEmpty))
        Mockito.verify(movieRepo).getFavorite()

        MutableLiveData(resourceEmpty).observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(resourceEmpty)
    }

    @Test
    fun getResourceFavoriteTVShowSuccess() {
        Mockito.`when`(tvShowRepo.getFavorite()).thenReturn(MutableLiveData(tvShowResourceSuccess))
        Mockito.verify(tvShowRepo).getFavorite()

        MutableLiveData(tvShowResourceSuccess).observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(tvShowResourceSuccess)

        assertEquals(getTvShow(), tvShowResourceSuccess.data.snapshot())
    }

    @Test
    fun getResourceFavoriteTVShowError() {
        Mockito.`when`(tvShowRepo.getFavorite()).thenReturn(MutableLiveData(resourceError))
        Mockito.verify(tvShowRepo).getFavorite()

        MutableLiveData(resourceError).observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(resourceError)
    }

    @Test
    fun getResourceFavoriteTVShowEmpty() {
        Mockito.`when`(tvShowRepo.getFavorite()).thenReturn(MutableLiveData(resourceEmpty))
        Mockito.verify(tvShowRepo).getFavorite()

        MutableLiveData(resourceEmpty).observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(resourceEmpty)
    }
}