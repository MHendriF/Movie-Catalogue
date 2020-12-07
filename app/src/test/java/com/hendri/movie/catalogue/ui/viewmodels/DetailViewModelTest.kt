package com.hendri.movie.catalogue.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.Resource
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
    private lateinit var viewModel: DetailViewModel

    @Mock
    lateinit var repository: MainRepository

    @Mock
    lateinit var observerMovie: Observer<Resource<DetailMovieResponse>>

    @Mock
    lateinit var observerTvShow: Observer<Resource<DetailTvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getDetailMovieResourceSuccess() {
        val dummyData = DummyDataResponse.detailMovieResponse()
        val dataDes = R.id.detail_movie
        val dataId = dummyData.id

        Mockito.`when`(repository.getMovieById(dataId)).thenReturn(MutableLiveData(Resource.Success(dummyData)))

        viewModel.setDataExtra(dataDes, dataId)
        assertEquals(dataDes, viewModel.getDataExtra(DetailViewModel.DATA_DESTINATION))
        assertEquals(dataId, viewModel.getDataExtra(DetailViewModel.DATA_ID))
        verify(repository).getMovieById(dataId)
        assertNotNull(viewModel.dataMovie)

        viewModel.dataMovie?.observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Success(dummyData))

        val resource = getValue(viewModel.dataMovie)
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
        val dummyData = DummyDataResponse.detailTvShowResponse()
        val dataDes = R.id.detail_tv_show
        val dataId = dummyData.id

        Mockito.`when`(repository.getTvShowById(dataId)).thenReturn(MutableLiveData(Resource.Success(dummyData)))

        viewModel.setDataExtra(dataDes, dataId)
        assertEquals(dataDes, viewModel.getDataExtra(DetailViewModel.DATA_DESTINATION))
        assertEquals(dataId, viewModel.getDataExtra(DetailViewModel.DATA_ID))
        verify(repository).getTvShowById(dataId)
        assertNotNull(viewModel.dataTvShow)

        viewModel.dataTvShow?.observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Success(dummyData))

        val resource = getValue(viewModel.dataTvShow)
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