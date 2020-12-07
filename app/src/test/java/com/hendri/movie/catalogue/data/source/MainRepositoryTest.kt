package com.hendri.movie.catalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

    private lateinit var repository: MainRepositoryFake

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var observerMovie: Observer<Resource<MovieResponse>>

    @Mock
    lateinit var observerTvShow: Observer<Resource<TvShowResponse>>

    @Mock
    lateinit var observerDetailMovie: Observer<Resource<DetailMovieResponse>>

    @Mock
    lateinit var observerDetailTvShow: Observer<Resource<DetailTvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = MainRepositoryFake(remoteDataSource)
    }

    @Test
    fun movieResourceSuccess() {
        val dummyData = DummyDataResponse.movieResponse()

        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(repository.getMovies())

        verify(remoteDataSource).getMovies()

        repository.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Success(dummyData))

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
    fun movieResourceEmpty() {
        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(repository.getMovies())

        verify(remoteDataSource).getMovies()

        repository.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    @Test
    fun movieResourceError() {
        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(repository.getMovies())

        verify(remoteDataSource).getMovies()

        repository.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

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
    fun tvShowResourceSuccess() {
        val dummyData = DummyDataResponse.tvShowResponse()

        Mockito.`when`(remoteDataSource.getTvShows()).thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(repository.getTvShows())

        verify(remoteDataSource).getTvShows()

        repository.getTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Success(dummyData))

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
    fun tvShowResourceEmpty() {
        Mockito.`when`(remoteDataSource.getTvShows()).thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(repository.getTvShows())

        verify(remoteDataSource).getTvShows()

        repository.getTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    @Test
    fun tvShowResourceError() {
        Mockito.`when`(remoteDataSource.getTvShows())
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(repository.getTvShows())

        verify(remoteDataSource).getTvShows()

        repository.getTvShows().observeForever(observerTvShow)
        verify(observerTvShow).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

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
    fun detailMovieSourceSuccess() {
        val dummyData = DummyDataResponse.detailMovieResponse()
        val idData = dummyData.id

        Mockito.`when`(remoteDataSource.getMovieById(idData)).thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(repository.getMovieById(idData))

        verify(remoteDataSource).getMovieById(idData)

        repository.getMovieById(idData).observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Success(dummyData))

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
    fun detailMovieSourceError() {
        val idData = DummyDataResponse.detailMovieResponse().id
        Mockito.`when`(remoteDataSource.getMovieById(idData)).thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(repository.getMovieById(idData))

        verify(remoteDataSource).getMovieById(idData)

        repository.getMovieById(idData)
            .observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

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
    fun detailMovieSourceEmpty() {
        val idData = -500
        Mockito.`when`(remoteDataSource.getMovieById(idData)).thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(repository.getMovieById(idData))

        verify(remoteDataSource).getMovieById(idData)

        repository.getMovieById(idData)
            .observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)
        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }

    @Test
    fun detailTvShowSourceSuccess() {
        val dummyData = DummyDataResponse.detailTvShowResponse()
        val idData = dummyData.id

        Mockito.`when`(remoteDataSource.getTvShowById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Success(dummyData)))

        val resource = getValue(repository.getTvShowById(idData))

        verify(remoteDataSource).getTvShowById(idData)

        repository.getTvShowById(idData).observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Success(dummyData))

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
    fun detailTvShowSourceError() {
        val idData = DummyDataResponse.detailTvShowResponse().id
        Mockito.`when`(remoteDataSource.getTvShowById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Error(RemoteDataSourceTest.errorMessage)))

        val resource = getValue(repository.getTvShowById(idData))

        verify(remoteDataSource).getTvShowById(idData)

        repository.getTvShowById(idData).observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Error(RemoteDataSourceTest.errorMessage))

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
    fun detailTvShowSourceEmpty() {
        val idData = -500
        Mockito.`when`(remoteDataSource.getTvShowById(idData))
            .thenReturn(MutableLiveData(ApiResponse.Empty(null)))

        val resource = getValue(repository.getTvShowById(idData))

        verify(remoteDataSource).getTvShowById(idData)

        repository.getTvShowById(idData).observeForever(observerDetailTvShow)
        verify(observerDetailTvShow).onChanged(Resource.Empty(null))

        Assert.assertNotNull(resource)
        Assert.assertTrue(resource is Resource.Empty)

        when (resource) {
            is Resource.Empty -> {
                Assert.assertNull(resource.data)
            }
        }
    }
}