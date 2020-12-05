package com.hendri.movie.catalogue.data.source.remote.network

import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.Constants
import com.hendri.movie.catalogue.utils.Constants.TIME_OUT
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class ApiServiceTest {
    private val apiService = RetrofitBuilder.service(Constants.TMDB_BASE_URL, ApiService::class.java)
    private val contDownLatch = CountDownLatch(1)

    @Test
    fun getMovieFromApi() {
        apiService.getMovies()?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                contDownLatch.countDown()
                print(response.body()?.results)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                contDownLatch.countDown()
                t.printStackTrace()
            }

        })
        contDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }

    @Test
    fun getTvShowFromApi() {
        apiService.getTvShows()?.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, showResponse: Response<TvShowResponse>) {
                contDownLatch.countDown()
                print(showResponse.body()?.results)
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        contDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }

    @Test
    fun getMovieByIdFromApi() {
        apiService.getMovieById(650747).enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>, movieResponse: Response<DetailMovieResponse>
            ) {
                contDownLatch.countDown()
                print(movieResponse.body())
            }
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        contDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }

    @Test
    fun getTvShowByIdFromApi() {
        apiService.getTvShowById(71712).enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>, tvShowResponse: Response<DetailTvShowResponse>
            ) {
                contDownLatch.countDown()
                print(tvShowResponse.body())
            }
            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        contDownLatch.await(TIME_OUT, TimeUnit.SECONDS)
    }
}