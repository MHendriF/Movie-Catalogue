package com.hendri.movie.catalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSourceTest
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvResponse
import com.hendri.movie.catalogue.utils.DummyDataResponse
import com.hendri.movie.catalogue.utils.LiveDataTestUtil.getValue
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    private lateinit var mainRepository: MainRepositoryFake

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var observerResourceMovieResponse: Observer<Resource<MovieResponse>>

    @Mock
    lateinit var observerResourceTvResponse: Observer<Resource<TvResponse>>

    @Mock
    lateinit var observerResourceDetailMovieResponse: Observer<Resource<MovieDetailResponse>>

    @Mock
    lateinit var observerResourceDetailTvResponse: Observer<Resource<TvDetailResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainRepository = MainRepositoryFake(remoteDataSource)
    }

    @Test
    fun get_data_movie_resource_success() {
        val dummyData = DummyDataResponse.movieResponse()

        Mockito.`when`(remoteDataSource.getDataMovie())
            .thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getDataMovie())

        verify(remoteDataSource).getDataMovie()

        mainRepository.getDataMovie().observeForever(observerResourceMovieResponse)
        verify(observerResourceMovieResponse).onChanged(Resource.Success(dummyData))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyData, resource.data)
                Assert.assertTrue(resource.data.results.isNotEmpty())
            }
        }
    }

    @Test
    fun get_data_movie_resource_empty() {
        Mockito.`when`(remoteDataSource.getDataMovie())
            .thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getDataMovie())

        verify(remoteDataSource).getDataMovie()

        mainRepository.getDataMovie().observeForever(observerResourceMovieResponse)
        verify(observerResourceMovieResponse).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    @Test
    fun get_data_movie_resource_error() {
        Mockito.`when`(remoteDataSource.getDataMovie())
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getDataMovie())

        verify(remoteDataSource).getDataMovie()

        mainRepository.getDataMovie().observeForever(observerResourceMovieResponse)
        verify(observerResourceMovieResponse).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)

        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun get_data_tv_resource_success() {
        val dummyData = DummyDataResponse.tvResponse()

        Mockito.`when`(remoteDataSource.getDataTv())
            .thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getDataTv())

        verify(remoteDataSource).getDataTv()

        mainRepository.getDataTv().observeForever(observerResourceTvResponse)
        verify(observerResourceTvResponse).onChanged(Resource.Success(dummyData))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyData, resource.data)
                Assert.assertTrue(resource.data.results.isNotEmpty())
            }
        }
    }

    @Test
    fun get_data_tv_resource_empty() {
        Mockito.`when`(remoteDataSource.getDataTv())
            .thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getDataTv())

        verify(remoteDataSource).getDataTv()

        mainRepository.getDataTv().observeForever(observerResourceTvResponse)
        verify(observerResourceTvResponse).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    @Test
    fun get_data_tv_resource_error() {
        Mockito.`when`(remoteDataSource.getDataTv())
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getDataTv())

        verify(remoteDataSource).getDataTv()

        mainRepository.getDataTv().observeForever(observerResourceTvResponse)
        verify(observerResourceTvResponse).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)

        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun get_detail_data_movie_resource_success() {
        val dummyData = DummyDataResponse.movieDetailResponse()
        val idData = dummyData.id

        Mockito.`when`(remoteDataSource.getDataMovieById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getDataMovieById(idData))

        verify(remoteDataSource).getDataMovieById(idData)

        mainRepository.getDataMovieById(idData)
            .observeForever(observerResourceDetailMovieResponse)
        verify(observerResourceDetailMovieResponse).onChanged(Resource.Success(dummyData))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyData, resource.data)
                Assert.assertTrue(resource.data.id == resource.data.id)
            }
        }
    }

    @Test
    fun get_detail_data_movie_resource_error() {
        val idData = DummyDataResponse.movieDetailResponse().id
        Mockito.`when`(remoteDataSource.getDataMovieById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getDataMovieById(idData))

        verify(remoteDataSource).getDataMovieById(idData)

        mainRepository.getDataMovieById(idData)
            .observeForever(observerResourceDetailMovieResponse)
        verify(observerResourceDetailMovieResponse).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)
        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun get_detail_data_movie_resource_empty() {
        val idData = -500
        Mockito.`when`(remoteDataSource.getDataMovieById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getDataMovieById(idData))

        verify(remoteDataSource).getDataMovieById(idData)

        mainRepository.getDataMovieById(idData)
            .observeForever(observerResourceDetailMovieResponse)
        verify(observerResourceDetailMovieResponse).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    @Test
    fun get_detail_data_tv_resource_success() {
        val dummyData = DummyDataResponse.tvDetailResponse()
        val idData = dummyData.id

        Mockito.`when`(remoteDataSource.getDataTvById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getDataTvById(idData))

        verify(remoteDataSource).getDataTvById(idData)

        mainRepository.getDataTvById(idData)
            .observeForever(observerResourceDetailTvResponse)
        verify(observerResourceDetailTvResponse).onChanged(Resource.Success(dummyData))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Success)

        when (resource) {
            is Resource.Success -> {
                Assert.assertNotNull(resource.data)
                assertEquals(dummyData, resource.data)
                Assert.assertTrue(resource.data.id == resource.data.id)
            }
        }
    }

    @Test
    fun get_detail_data_tv_resource_error() {
        val idData = DummyDataResponse.tvDetailResponse().id
        Mockito.`when`(remoteDataSource.getDataTvById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getDataTvById(idData))

        verify(remoteDataSource).getDataTvById(idData)

        mainRepository.getDataTvById(idData).observeForever(observerResourceDetailTvResponse)
        verify(observerResourceDetailTvResponse).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Error)

        when (resource) {
            is Resource.Error -> {
                Assert.assertNotNull(resource.errorMessage)
                assertEquals(RemoteDataSourceTest.errorMessage, resource.errorMessage)
            }
        }
    }

    @Test
    fun get_detail_data_tv_resource_empty() {
        val idData = -500
        Mockito.`when`(remoteDataSource.getDataTvById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getDataTvById(idData))

        verify(remoteDataSource).getDataTvById(idData)

        mainRepository.getDataTvById(idData).observeForever(observerResourceDetailTvResponse)
        verify(observerResourceDetailTvResponse).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }
}