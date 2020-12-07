package com.hendri.movie.catalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.data.Resource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSourceTest
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
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
    lateinit var observerResourceTvShowResponse: Observer<Resource<TvShowResponse>>

    @Mock
    lateinit var observerResourceDetailMovieResponse: Observer<Resource<DetailMovieResponse>>

    @Mock
    lateinit var observerResourceDetailTvShowResponse: Observer<Resource<DetailTvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainRepository = MainRepositoryFake(remoteDataSource)
    }

    @Test
    fun get_data_movie_resource_success() {
        val dummyData = DummyDataResponse.movieResponse()

        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getMovies())

        verify(remoteDataSource).getMovies()

        mainRepository.getMovies().observeForever(observerResourceMovieResponse)
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
        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getMovies())

        verify(remoteDataSource).getMovies()

        mainRepository.getMovies().observeForever(observerResourceMovieResponse)
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
        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getMovies())

        verify(remoteDataSource).getMovies()

        mainRepository.getMovies().observeForever(observerResourceMovieResponse)
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
        val dummyData = DummyDataResponse.tvShowResponse()

        Mockito.`when`(remoteDataSource.getTvShows()).thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getTvShows())

        verify(remoteDataSource).getTvShows()

        mainRepository.getTvShows().observeForever(observerResourceTvShowResponse)
        verify(observerResourceTvShowResponse).onChanged(Resource.Success(dummyData))

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
        Mockito.`when`(remoteDataSource.getTvShows()).thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getTvShows())

        verify(remoteDataSource).getTvShows()

        mainRepository.getTvShows().observeForever(observerResourceTvShowResponse)
        verify(observerResourceTvShowResponse).onChanged(Resource.Empty(null))

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
        Mockito.`when`(remoteDataSource.getTvShows())
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getTvShows())

        verify(remoteDataSource).getTvShows()

        mainRepository.getTvShows().observeForever(observerResourceTvShowResponse)
        verify(observerResourceTvShowResponse).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

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
        val dummyData = DummyDataResponse.detailMovieResponse()
        val idData = dummyData.id

        Mockito.`when`(remoteDataSource.getMovieById(idData)).thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getMovieById(idData))

        verify(remoteDataSource).getMovieById(idData)

        mainRepository.getMovieById(idData).observeForever(observerResourceDetailMovieResponse)
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
        val idData = DummyDataResponse.detailMovieResponse().id
        Mockito.`when`(remoteDataSource.getMovieById(idData)).thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getMovieById(idData))

        verify(remoteDataSource).getMovieById(idData)

        mainRepository.getMovieById(idData)
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
        Mockito.`when`(remoteDataSource.getMovieById(idData)).thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getMovieById(idData))

        verify(remoteDataSource).getMovieById(idData)

        mainRepository.getMovieById(idData)
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
        val dummyData = DummyDataResponse.detailTvShowResponse()
        val idData = dummyData.id

        Mockito.`when`(remoteDataSource.getTvShowById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(mainRepository.getTvShowById(idData))

        verify(remoteDataSource).getTvShowById(idData)

        mainRepository.getTvShowById(idData).observeForever(observerResourceDetailTvShowResponse)
        verify(observerResourceDetailTvShowResponse).onChanged(Resource.Success(dummyData))

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
        val idData = DummyDataResponse.detailTvShowResponse().id
        Mockito.`when`(remoteDataSource.getTvShowById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(mainRepository.getTvShowById(idData))

        verify(remoteDataSource).getTvShowById(idData)

        mainRepository.getTvShowById(idData).observeForever(observerResourceDetailTvShowResponse)
        verify(observerResourceDetailTvShowResponse).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

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
        Mockito.`when`(remoteDataSource.getTvShowById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(mainRepository.getTvShowById(idData))

        verify(remoteDataSource).getTvShowById(idData)

        mainRepository.getTvShowById(idData).observeForever(observerResourceDetailTvShowResponse)
        verify(observerResourceDetailTvShowResponse).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }
}