package com.hendri.movie.catalogue.data.source.remote

import androidx.lifecycle.LiveData
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvResponse

interface IRemoteDataSource {
    fun getDataMovie(): LiveData<ApiResponse<MovieResponse>>
    fun getDataTv(): LiveData<ApiResponse<TvResponse>>
    fun getDataMovieById(id: Int): LiveData<ApiResponse<MovieDetailResponse>>
    fun getDataTvById(id: Int): LiveData<ApiResponse<TvDetailResponse>>
}