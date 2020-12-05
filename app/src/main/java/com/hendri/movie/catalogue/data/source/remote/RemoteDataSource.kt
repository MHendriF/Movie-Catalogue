package com.hendri.movie.catalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.network.ApiService
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class RemoteDataSource private constructor(
    private val apiService: ApiService
) : IRemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) { instance ?: RemoteDataSource(apiService) }
    }

    override fun getMovies(): LiveData<ApiResponse<MovieResponse>> {
        val data = MutableLiveData<ApiResponse<MovieResponse>>()
        apiService.getDataMovie()?.enqueue(enqueueCallback(data))
        return data
    }

    override fun getTvShows(): LiveData<ApiResponse<TvShowResponse>> {
        val data = MutableLiveData<ApiResponse<TvShowResponse>>()
        apiService.getDataTv()?.enqueue(enqueueCallback(data))
        return data
    }

    override fun getMovieById(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        val data = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        apiService.getDataMovieById(id).enqueue(enqueueCallback(data))
        return data
    }

    override fun getTvShowById(id: Int): LiveData<ApiResponse<DetailTvShowResponse>> {
        val data = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        apiService.getDataTvById(id).enqueue(enqueueCallback(data))
        return data
    }

    private fun <T> enqueueCallback(mutableLiveData: MutableLiveData<ApiResponse<T>>): Callback<T?> {
        EspressoIdlingResource.increment()
        return object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                val data = response.body()
                mutableLiveData.postValue(
                    if (data != null) ApiResponse.Success(data) else ApiResponse.Empty()
                )
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                mutableLiveData.value = ApiResponse.Error(t.message.toString())
                Timber.e(t.message.toString())
                EspressoIdlingResource.decrement()
            }
        }
    }
}