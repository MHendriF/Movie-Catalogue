package com.hendri.movie.catalogue.data.source.remote

import androidx.lifecycle.LiveData
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse

interface IRemoteDataSource {
    fun getMovies(): LiveData<ApiResponse<MovieResponse>>
    fun getTvShows(): LiveData<ApiResponse<TvShowResponse>>
    fun getMovieById(id: Int): LiveData<ApiResponse<DetailMovieResponse>>
    fun getTvShowById(id: Int): LiveData<ApiResponse<DetailTvShowResponse>>
}