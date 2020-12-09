package com.hendri.movie.catalogue.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.network.ApiService
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.DummyDataResponse
import com.hendri.movie.catalogue.utils.LiveDataTestUtil.getValue
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    companion object {
        const val errorMessage = "error message"
    }

    private lateinit var remoteDataSource: RemoteDataSourceFake

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var callMovieResponse: Call<MovieResponse>

    @Mock
    private lateinit var callTvShowResponse: Call<TvShowResponse>

    @Mock
    private lateinit var callDetailMovieResponse: Call<DetailMovieResponse>

    @Mock
    private lateinit var callDetailTvShowResponse: Call<DetailTvShowResponse>

    @Mock
    private lateinit var observerApiResponseMovie: Observer<ApiResponse<MovieResponse>>

    @Mock
    private lateinit var observerApiResponseTvShow: Observer<ApiResponse<TvShowResponse>>

    @Mock
    private lateinit var observerApiResponseDetailMovie: Observer<ApiResponse<DetailMovieResponse>>

    @Mock
    private lateinit var observerApiResponseDetailTvShow: Observer<ApiResponse<DetailTvShowResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSourceFake(apiService)
    }

    @Test
    fun movieResponseSuccess() {
        val dataDummy = DummyDataResponse.movieResponse()
        Mockito.`when`(apiService.getMovies()).thenReturn(callMovieResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<MovieResponse>).onResponse(callMovieResponse, Response.success(dataDummy))
        }.`when`(callMovieResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getMovies())

        Mockito.verify(apiService).getMovies()
        Mockito.verify(apiService.getMovies())?.enqueue(Mockito.any())

        remoteDataSource.getMovies().observeForever(observerApiResponseMovie)
        Mockito.verify(observerApiResponseMovie).onChanged(ApiResponse.Success(dataDummy))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Success)
        when (apiResponse) {
            is ApiResponse.Success -> {
                assertEquals(dataDummy, apiResponse.data)
                assertEquals(dataDummy.results, apiResponse.data.results)
                assertEquals(dataDummy.results.size, apiResponse.data.results.size)
            }
        }
    }

    @Test
    fun movieResponseError() {
        Mockito.`when`(apiService.getMovies()).thenReturn(callMovieResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<MovieResponse>).onFailure(callMovieResponse, Throwable(errorMessage))
        }.`when`(callMovieResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getMovies())

        Mockito.verify(apiService).getMovies()
        Mockito.verify(apiService.getMovies())?.enqueue(Mockito.any())

        remoteDataSource.getMovies().observeForever(observerApiResponseMovie)
        Mockito.verify(observerApiResponseMovie).onChanged(ApiResponse.Error(errorMessage))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Error)
        when (apiResponse) {
            is ApiResponse.Error -> {
                assertEquals(errorMessage, apiResponse.message)
            }
        }
    }

    @Test
    fun movieResponseEmpty() {
        Mockito.`when`(apiService.getMovies()).thenReturn(callMovieResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<MovieResponse>).onResponse(callMovieResponse, Response.success(null))
        }.`when`(callMovieResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getMovies())

        Mockito.verify(apiService).getMovies()
        Mockito.verify(apiService.getMovies())?.enqueue(Mockito.any())

        remoteDataSource.getMovies().observeForever(observerApiResponseMovie)
        Mockito.verify(observerApiResponseMovie).onChanged(ApiResponse.Empty(null))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Empty)
        when (apiResponse) {
            is ApiResponse.Empty -> {
                Assert.assertNull(apiResponse.data)
            }
        }
    }

    @Test
    fun tvShowResponseSuccess() {
        val dataDummy = DummyDataResponse.tvShowResponse()
        Mockito.`when`(apiService.getTvShows()).thenReturn(callTvShowResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<TvShowResponse>).onResponse(callTvShowResponse, Response.success(dataDummy))
        }.`when`(callTvShowResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getTvShows())

        Mockito.verify(apiService).getTvShows()
        Mockito.verify(apiService.getTvShows())?.enqueue(Mockito.any())

        remoteDataSource.getTvShows().observeForever(observerApiResponseTvShow)
        Mockito.verify(observerApiResponseTvShow).onChanged(ApiResponse.Success(dataDummy))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Success)
        when (apiResponse) {
            is ApiResponse.Success -> {
                assertEquals(dataDummy, apiResponse.data)
                assertEquals(dataDummy.results, apiResponse.data.results)
                assertEquals(dataDummy.results.size, apiResponse.data.results.size)
            }
        }
    }

    @Test
    fun tvShowResponseError() {
        Mockito.`when`(apiService.getTvShows()).thenReturn(callTvShowResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<TvShowResponse>)
                .onFailure(callTvShowResponse, Throwable(errorMessage))
        }.`when`(callTvShowResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getTvShows())

        Mockito.verify(apiService).getTvShows()
        Mockito.verify(apiService.getTvShows())?.enqueue(Mockito.any())

        remoteDataSource.getTvShows().observeForever(observerApiResponseTvShow)
        Mockito.verify(observerApiResponseTvShow).onChanged(ApiResponse.Error(errorMessage))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Error)
        when (apiResponse) {
            is ApiResponse.Error -> {
                assertEquals(errorMessage, apiResponse.message)
            }
        }
    }

    @Test
    fun tvShowResponseEmpty() {
        Mockito.`when`(apiService.getTvShows()).thenReturn(callTvShowResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<TvShowResponse>)
                .onResponse(callTvShowResponse, Response.success(null))
        }.`when`(callTvShowResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getTvShows())

        Mockito.verify(apiService).getTvShows()
        Mockito.verify(apiService.getTvShows())?.enqueue(Mockito.any())

        remoteDataSource.getTvShows().observeForever(observerApiResponseTvShow)
        Mockito.verify(observerApiResponseTvShow).onChanged(ApiResponse.Empty(null))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Empty)
        when (apiResponse) {
            is ApiResponse.Empty -> {
                Assert.assertNull(apiResponse.data)
            }
        }
    }

    @Test
    fun detailMovieResponseSuccess() {
        val dataDummy = DummyDataResponse.detailMovieResponse()
        val idData = dataDummy.id
        Mockito.`when`(apiService.getMovieById(idData)).thenReturn(callDetailMovieResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<DetailMovieResponse>)
                .onResponse(callDetailMovieResponse, Response.success(dataDummy))
        }.`when`(callDetailMovieResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getMovieById(idData))

        Mockito.verify(apiService).getMovieById(idData)
        Mockito.verify(apiService.getMovieById(idData))?.enqueue(Mockito.any())

        remoteDataSource.getMovieById(idData).observeForever(observerApiResponseDetailMovie)
        Mockito.verify(observerApiResponseDetailMovie).onChanged(ApiResponse.Success(dataDummy))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Success)
        when (apiResponse) {
            is ApiResponse.Success -> {
                assertEquals(dataDummy, apiResponse.data)
            }
        }
    }

    @Test
    fun detailTvShowResponseSuccess() {
        val dataDummy = DummyDataResponse.detailTvShowResponse()
        val idData = dataDummy.id
        Mockito.`when`(apiService.getTvShowById(idData)).thenReturn(callDetailTvShowResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<DetailTvShowResponse>)
                .onResponse(callDetailTvShowResponse, Response.success(dataDummy))
        }.`when`(callDetailTvShowResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getTvShowById(idData))

        Mockito.verify(apiService).getTvShowById(idData)
        Mockito.verify(apiService.getTvShowById(idData))?.enqueue(Mockito.any())

        remoteDataSource.getTvShowById(idData).observeForever(observerApiResponseDetailTvShow)
        Mockito.verify(observerApiResponseDetailTvShow).onChanged(ApiResponse.Success(dataDummy))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Success)
        when (apiResponse) {
            is ApiResponse.Success -> {
                assertEquals(dataDummy, apiResponse.data)
            }
        }
    }
}