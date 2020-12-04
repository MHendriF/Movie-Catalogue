package com.hendri.movie.catalogue.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.Resource
import com.hendri.movie.catalogue.data.source.remote.response.MovieDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import com.hendri.movie.catalogue.utils.DummyDataResponse
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
    private lateinit var detailViewModel: DetailViewModel

    @Mock
    lateinit var repository: MainRepository

    @Mock
    lateinit var observerMovieDetail: Observer<Resource<MovieDetailResponse>>

    @Mock
    lateinit var observerTvDetail: Observer<Resource<TvDetailResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(repository)
    }

    @Test
    fun detail_data_movie_resource_success() {
        val dummyData = DummyDataResponse.movieDetailResponse()
        val dataDes = R.id.detail_movie
        val dataId = dummyData.id

        Mockito.`when`(repository.getDataMovieById(dataId))
            .thenReturn(MutableLiveData(Resource.Success(dummyData)))

        detailViewModel.setDataExtra(dataDes, dataId)
        assertEquals(dataDes, detailViewModel.getDataExtra(DetailViewModel.DATA_DESTINATION))
        assertEquals(dataId, detailViewModel.getDataExtra(DetailViewModel.DATA_ID))
        verify(repository).getDataMovieById(dataId)
        assertNotNull(detailViewModel.dataMovie)

        detailViewModel.dataMovie?.observeForever(observerMovieDetail)
        verify(observerMovieDetail).onChanged(Resource.Success(dummyData))

        val resource = getValue(detailViewModel.dataMovie)
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
    fun detail_data_tv_resource_success() {
        val dummyData = DummyDataResponse.tvDetailResponse()
        val dataDes = R.id.detail_tv
        val dataId = dummyData.id

        Mockito.`when`(repository.getDataTvById(dataId))
            .thenReturn(MutableLiveData(Resource.Success(dummyData)))

        detailViewModel.setDataExtra(dataDes, dataId)
        assertEquals(dataDes, detailViewModel.getDataExtra(DetailViewModel.DATA_DESTINATION))
        assertEquals(dataId, detailViewModel.getDataExtra(DetailViewModel.DATA_ID))
        verify(repository).getDataTvById(dataId)
        assertNotNull(detailViewModel.dataTv)

        detailViewModel.dataTv?.observeForever(observerTvDetail)
        verify(observerTvDetail).onChanged(Resource.Success(dummyData))

        val resource = getValue(detailViewModel.dataTv)
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