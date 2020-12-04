package com.hendri.movie.catalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.network.ApiService
import com.hendri.movie.catalogue.data.source.remote.response.MovieDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceFake(private val apiService: ApiService) : IRemoteDataSource {
    override fun getDataMovie(): LiveData<ApiResponse<MovieResponse>> {
        val data = MutableLiveData<ApiResponse<MovieResponse>>()
        apiService.getDataMovie()?.enqueue(enqueueCallback(data))
        return data
    }

    override fun getDataTv(): LiveData<ApiResponse<TvResponse>> {
        val data = MutableLiveData<ApiResponse<TvResponse>>()
        apiService.getDataTv()?.enqueue(enqueueCallback(data))
        return data
    }

    override fun getDataMovieById(id: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        val data = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        apiService.getDataMovieById(id).enqueue(enqueueCallback(data))
        return data
    }

    override fun getDataTvById(id: Int): LiveData<ApiResponse<TvDetailResponse>> {
        val data = MutableLiveData<ApiResponse<TvDetailResponse>>()
        apiService.getDataTvById(id).enqueue(enqueueCallback(data))
        return data
    }

    private fun <T> enqueueCallback(mutableLiveData: MutableLiveData<ApiResponse<T>>): Callback<T?> {
        return object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                val data = response.body()
                mutableLiveData.postValue(
                    if (data != null) ApiResponse.Success(data) else ApiResponse.Empty(data)
                )
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                mutableLiveData.postValue(ApiResponse.Error(t.message.toString()))
            }
        }
    }
}