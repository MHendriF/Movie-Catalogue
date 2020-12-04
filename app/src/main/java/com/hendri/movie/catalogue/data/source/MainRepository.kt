package com.hendri.movie.catalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvResponse

class MainRepository private constructor(private val remoteDataSource: RemoteDataSource){
    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MainRepository =
            instance ?: synchronized(this) { instance ?: MainRepository(remoteDataSource) }
    }

    private fun <T> result(
        result: MediatorLiveData<Resource<T>>, response: LiveData<ApiResponse<T>>
    ): LiveData<Resource<T>> {
        result.postValue(Resource.Loading())
        result.addSource(response) {
            result.removeSource(response)
            when (it) {
                is ApiResponse.Success -> result.postValue(Resource.Success(it.data))
                is ApiResponse.Error -> result.postValue(Resource.Error(it.errorMessage))
                is ApiResponse.Empty -> result.postValue(Resource.Empty(it.data))
            }
        }
        return result
    }

    fun getDataMovie(): LiveData<Resource<MovieResponse>> {
        return result(
            MediatorLiveData<Resource<MovieResponse>>(),
            remoteDataSource.getDataMovie()
        )
    }

    fun getDataTv(): LiveData<Resource<TvResponse>> {
        return result(
            MediatorLiveData<Resource<TvResponse>>(),
            remoteDataSource.getDataTv()
        )
    }

    fun getDataTvById(id: Int): LiveData<Resource<TvDetailResponse>> {
        return result(
            MediatorLiveData<Resource<TvDetailResponse>>(),
            remoteDataSource.getDataTvById(id)
        )
    }

    fun getDataMovieById(id: Int): LiveData<Resource<MovieDetailResponse>> {
        return result(
            MediatorLiveData<Resource<MovieDetailResponse>>(),
            remoteDataSource.getDataMovieById(id)
        )
    }
}