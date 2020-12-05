package com.hendri.movie.catalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse

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

    fun getMovies(): LiveData<Resource<MovieResponse>> {
        return result(
            MediatorLiveData<Resource<MovieResponse>>(),
            remoteDataSource.getMovies()
        )
    }

    fun getTvShows(): LiveData<Resource<TvShowResponse>> {
        return result(
            MediatorLiveData<Resource<TvShowResponse>>(),
            remoteDataSource.getTvShows()
        )
    }

    fun getMovieById(id: Int): LiveData<Resource<DetailMovieResponse>> {
        return result(
            MediatorLiveData<Resource<DetailMovieResponse>>(),
            remoteDataSource.getMovieById(id)
        )
    }

    fun getTvShowById(id: Int): LiveData<Resource<DetailTvShowResponse>> {
        return result(
            MediatorLiveData<Resource<DetailTvShowResponse>>(),
            remoteDataSource.getTvShowById(id)
        )
    }
}