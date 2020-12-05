package com.hendri.movie.catalogue.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.Resource
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
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
    lateinit var observerDetailMovie: Observer<Resource<DetailMovieResponse>>

    @Mock
    lateinit var observerDetailTvShow: Observer<Resource<DetailTvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(repository)
    }

    @Test
    fun detail_movie_resource_success() {
        val dummyData = DummyDataResponse.detailMovieResponse()
        val dataDes = R.id.detail_movie
        val dataId = dummyData.id

        Mockito.`when`(repository.getMovieById(dataId)).thenReturn(MutableLiveData(Resource.Success(dummyData)))

        detailViewModel.setDataExtra(dataDes, dataId)
        assertEquals(dataDes, detailViewModel.getDataExtra(DetailViewModel.DATA_DESTINATION))
        assertEquals(dataId, detailViewModel.getDataExtra(DetailViewModel.DATA_ID))
        verify(repository).getMovieById(dataId)
        assertNotNull(detailViewModel.dataMovie)

        detailViewModel.dataMovie?.observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Success(dummyData))

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
    fun detail_tv_show_resource_success() {
        val dummyData = DummyDataResponse.detailTvShowResponse()
        val dataDes = R.id.detail_tv
        val dataId = dummyData.id

        Mockito.`when`(repository.getTvShowById(dataId)).thenReturn(MutableLiveData(Resource.Success(dummyData)))

        detailViewModel.setDataExtra(dataDes, dataId)
        assertEquals(dataDes, detailViewModel.getDataExtra(DetailViewModel.DATA_DESTINATION))
        assertEquals(dataId, detailViewModel.getDataExtra(DetailViewModel.DATA_ID))
        verify(repository).getTvShowById(dataId)
        assertNotNull(detailViewModel.dataTvShow)

        detailViewModel.dataTvShow?.observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Success(dummyData))

        val resource = getValue(detailViewModel.dataTvShow)
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