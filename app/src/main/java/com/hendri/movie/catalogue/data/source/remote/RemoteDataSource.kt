package com.hendri.movie.catalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hendri.movie.catalogue.data.source.remote.api.RetrofitClient
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MoviesResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowsResponse
import com.hendri.movie.catalogue.utils.Constants
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RemoteDataSource private constructor(private val retrofitClient: RetrofitClient) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(client: RetrofitClient): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(client)
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        val moviesResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        val networkService = retrofitClient.generateRetrofitClient()

        EspressoIdlingResource.increment()

        networkService.getMovies(apiKey = Constants.TMDB_API_KEY)
            .enqueue(object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Timber.d("getMovies :: ${t.message})")
                    EspressoIdlingResource.decrement()
                }
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        moviesResponse.postValue(ApiResponse.success(data?.results))
                        EspressoIdlingResource.decrement()
                    }
                }
            })
        return moviesResponse
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        val tvShowResponse = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        val networkService = retrofitClient.generateRetrofitClient()

        EspressoIdlingResource.increment()

        networkService.getTvShows(apiKey = Constants.TMDB_API_KEY)
            .enqueue(object : Callback<TvShowsResponse> {
                override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                    Timber.d("getTvShows :: ${t.message})")
                    EspressoIdlingResource.decrement()
                }
                override fun onResponse(
                    call: Call<TvShowsResponse>,
                    response: Response<TvShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        tvShowResponse.postValue(ApiResponse.success(data?.results))
                        EspressoIdlingResource.decrement()
                    }
                }

            })
        return tvShowResponse
    }

    fun getMovieById(id: Int): LiveData<ApiResponse<MovieResponse>> {
        val movieResponse = MutableLiveData<ApiResponse<MovieResponse>>()
        val networkService = retrofitClient.generateRetrofitClient()

        EspressoIdlingResource.increment()

        networkService.getMovieById(id, apiKey = Constants.TMDB_API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Timber.d("getMovie :: ${t.message})")
                    EspressoIdlingResource.decrement()
                }
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        movieResponse.postValue(ApiResponse.success(data))
                        EspressoIdlingResource.decrement()
                    }
                }

            })
        return movieResponse
    }

    fun getTvShowById(id: Int): LiveData<ApiResponse<TvShowResponse>> {
        val tvResponse = MutableLiveData<ApiResponse<TvShowResponse>>()
        val networkService = retrofitClient.generateRetrofitClient()

        EspressoIdlingResource.increment()

        networkService.getTvShowById(id, apiKey = Constants.TMDB_API_KEY)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    Timber.d("getTvShow :: ${t.message})")
                    EspressoIdlingResource.decrement()
                }
                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        tvResponse.postValue(ApiResponse.success(data))
                        EspressoIdlingResource.decrement()
                    }
                }

            })
        return tvResponse
    }
}

