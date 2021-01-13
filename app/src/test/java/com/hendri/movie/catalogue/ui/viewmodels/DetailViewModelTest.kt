package com.hendri.movie.catalogue.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.data.model.DetailMovie
import com.hendri.movie.catalogue.data.model.DetailTvShow
import com.hendri.movie.catalogue.data.repository.MovieRepository
import com.hendri.movie.catalogue.data.repository.TvShowRepository
import com.hendri.movie.catalogue.ui.activities.DetailActivity.Companion.DATA_DESTINATION
import com.hendri.movie.catalogue.ui.activities.DetailActivity.Companion.DATA_ID
import com.hendri.movie.catalogue.utils.DummyData
import com.hendri.movie.catalogue.utils.LiveDataTestUtil.getValue
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    private lateinit var viewModel: DetailViewModel

    @Mock
    lateinit var movieRepo: MovieRepository

    @Mock
    lateinit var tvShowRepo: TvShowRepository

    @Mock
    lateinit var observerMovie: Observer<Resource<DetailMovie>>

    @Mock
    lateinit var observerTvShow: Observer<Resource<DetailTvShow>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepo, tvShowRepo)
    }

    @Test
    fun getDetailMovieResourceSuccess() {
        val dummyData = DummyData.getDetailMovie()
        val dataDes = R.id.detail_movie
        val dataId = dummyData.id

        Mockito.`when`(movieRepo.getDetail(dataId)).thenReturn(MutableLiveData(Resource.Success(dummyData)))

        viewModel.init(dataDes, dataId)
        assertEquals(dataDes, viewModel.getExtra(DATA_DESTINATION))
        assertEquals(dataId, viewModel.getExtra(DATA_ID))
        verify(movieRepo).getDetail(dataId)
        assertNotNull(viewModel.movie)

        viewModel.movie.observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Success(dummyData))

        val resource = getValue(viewModel.movie)
        assertNotNull(resource)
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyData, resource.data)
                assertEquals(dataId, resource.data.id)
            }
        }
    }

    @Test
    fun getDetailTvShowResourceSuccess() {
        val dummyData = DummyData.getDetailTvShow()
        val dataDes = R.id.detail_tv_show
        val dataId = dummyData.id

        Mockito.`when`(tvShowRepo.getDetail(dataId)).thenReturn(MutableLiveData(Resource.Success(dummyData)))

        viewModel.init(dataDes, dataId)
        assertEquals(dataDes, viewModel.getExtra(DATA_DESTINATION))
        assertEquals(dataId, viewModel.getExtra(DATA_ID))
        verify(tvShowRepo).getDetail(dataId)
        assertNotNull(viewModel.tvShow)

        viewModel.tvShow.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Success(dummyData))

        val resource = getValue(viewModel.tvShow)
        assertNotNull(resource)
        assertTrue(resource is Resource.Success)
        when (resource) {
            is Resource.Success -> {
                assertEquals(dummyData, resource.data)
                assertEquals(dataId, resource.data.id)
            }
        }
    }
}