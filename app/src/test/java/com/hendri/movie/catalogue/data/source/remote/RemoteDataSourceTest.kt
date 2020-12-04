package com.hendri.movie.catalogue.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.network.ApiService
import com.hendri.movie.catalogue.data.source.remote.response.MovieDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvResponse
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
    private lateinit var callTvResponse: Call<TvResponse>

    @Mock
    private lateinit var callMovieDetailResponse: Call<MovieDetailResponse>

    @Mock
    private lateinit var callTvDetailResponse: Call<TvDetailResponse>

    @Mock
    private lateinit var observerApiResponseMovie: Observer<ApiResponse<MovieResponse>>

    @Mock
    private lateinit var observerApiResponseTv: Observer<ApiResponse<TvResponse>>

    @Mock
    private lateinit var observerApiResponseMovieDetail: Observer<ApiResponse<MovieDetailResponse>>

    @Mock
    private lateinit var observerApiResponseTvDetail: Observer<ApiResponse<TvDetailResponse>>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSourceFake(apiService)
    }

    @Test
    fun get_data_movie_response_success() {
        val dataDummy = DummyDataResponse.movieResponse()
        Mockito.`when`(apiService.getDataMovie()).thenReturn(callMovieResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<MovieResponse>)
                .onResponse(callMovieResponse, Response.success(dataDummy))
        }.`when`(callMovieResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataMovie())

        Mockito.verify(apiService).getDataMovie()
        Mockito.verify(apiService.getDataMovie())?.enqueue(Mockito.any())

        remoteDataSource.getDataMovie().observeForever(observerApiResponseMovie)
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
    fun get_data_movie_response_error() {
        Mockito.`when`(apiService.getDataMovie()).thenReturn(callMovieResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<MovieResponse>)
                .onFailure(callMovieResponse, Throwable(errorMessage))
        }.`when`(callMovieResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataMovie())

        Mockito.verify(apiService).getDataMovie()
        Mockito.verify(apiService.getDataMovie())?.enqueue(Mockito.any())

        remoteDataSource.getDataMovie().observeForever(observerApiResponseMovie)
        Mockito.verify(observerApiResponseMovie).onChanged(ApiResponse.Error(errorMessage))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Error)
        when (apiResponse) {
            is ApiResponse.Error -> {
                assertEquals(errorMessage, apiResponse.errorMessage)
            }
        }
    }

    @Test
    fun get_data_movie_response_empty() {
        Mockito.`when`(apiService.getDataMovie()).thenReturn(callMovieResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<MovieResponse>)
                .onResponse(callMovieResponse, Response.success(null))
        }.`when`(callMovieResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataMovie())

        Mockito.verify(apiService).getDataMovie()
        Mockito.verify(apiService.getDataMovie())?.enqueue(Mockito.any())

        remoteDataSource.getDataMovie().observeForever(observerApiResponseMovie)
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
    fun get_data_tv_response_success() {
        val dataDummy = DummyDataResponse.tvResponse()
        Mockito.`when`(apiService.getDataTv()).thenReturn(callTvResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<TvResponse>)
                .onResponse(callTvResponse, Response.success(dataDummy))
        }.`when`(callTvResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataTv())

        Mockito.verify(apiService).getDataTv()
        Mockito.verify(apiService.getDataTv())?.enqueue(Mockito.any())

        remoteDataSource.getDataTv().observeForever(observerApiResponseTv)
        Mockito.verify(observerApiResponseTv).onChanged(ApiResponse.Success(dataDummy))

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
    fun get_data_tv_response_error() {
        Mockito.`when`(apiService.getDataTv()).thenReturn(callTvResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<TvResponse>)
                .onFailure(callTvResponse, Throwable(errorMessage))
        }.`when`(callTvResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataTv())

        Mockito.verify(apiService).getDataTv()
        Mockito.verify(apiService.getDataTv())?.enqueue(Mockito.any())

        remoteDataSource.getDataTv().observeForever(observerApiResponseTv)
        Mockito.verify(observerApiResponseTv).onChanged(ApiResponse.Error(errorMessage))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Error)
        when (apiResponse) {
            is ApiResponse.Error -> {
                assertEquals(errorMessage, apiResponse.errorMessage)
            }
        }
    }

    @Test
    fun get_data_tv_response_empty() {
        Mockito.`when`(apiService.getDataTv()).thenReturn(callTvResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<TvResponse>)
                .onResponse(callTvResponse, Response.success(null))
        }.`when`(callTvResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataTv())

        Mockito.verify(apiService).getDataTv()
        Mockito.verify(apiService.getDataTv())?.enqueue(Mockito.any())

        remoteDataSource.getDataTv().observeForever(observerApiResponseTv)
        Mockito.verify(observerApiResponseTv).onChanged(ApiResponse.Empty(null))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Empty)
        when (apiResponse) {
            is ApiResponse.Empty -> {
                Assert.assertNull(apiResponse.data)
            }
        }
    }

    @Test
    fun get_detail_movie_response_success() {
        val dataDummy = DummyDataResponse.movieDetailResponse()
        val idData = dataDummy.id
        Mockito.`when`(apiService.getDataMovieById(idData)).thenReturn(callMovieDetailResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<MovieDetailResponse>)
                .onResponse(callMovieDetailResponse, Response.success(dataDummy))
        }.`when`(callMovieDetailResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataMovieById(idData))

        Mockito.verify(apiService).getDataMovieById(idData)
        Mockito.verify(apiService.getDataMovieById(idData))?.enqueue(Mockito.any())

        remoteDataSource.getDataMovieById(idData).observeForever(observerApiResponseMovieDetail)
        Mockito.verify(observerApiResponseMovieDetail).onChanged(ApiResponse.Success(dataDummy))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Success)
        when (apiResponse) {
            is ApiResponse.Success -> {
                assertEquals(dataDummy, apiResponse.data)
            }
        }
    }

    @Test
    fun get_detail_tv_response_success() {
        val dataDummy = DummyDataResponse.tvDetailResponse()
        val idData = dataDummy.id
        Mockito.`when`(apiService.getDataTvById(idData)).thenReturn(callTvDetailResponse)

        Mockito.doAnswer {
            (it.arguments[0] as Callback<TvDetailResponse>)
                .onResponse(callTvDetailResponse, Response.success(dataDummy))
        }.`when`(callTvDetailResponse)?.enqueue(Mockito.any())

        val apiResponse = getValue(remoteDataSource.getDataTvById(idData))

        Mockito.verify(apiService).getDataTvById(idData)
        Mockito.verify(apiService.getDataTvById(idData))?.enqueue(Mockito.any())

        remoteDataSource.getDataTvById(idData).observeForever(observerApiResponseTvDetail)
        Mockito.verify(observerApiResponseTvDetail).onChanged(ApiResponse.Success(dataDummy))

        Assert.assertNotNull(apiResponse)
        Assert.assertTrue(apiResponse is ApiResponse.Success)
        when (apiResponse) {
            is ApiResponse.Success -> {
                assertEquals(dataDummy, apiResponse.data)
            }
        }
    }
}