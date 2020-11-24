package com.hendri.movie.catalogue.data.api

import com.hendri.movie.catalogue.data.response.MovieResponse
import com.hendri.movie.catalogue.data.response.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    @GET("discover/tv")
    suspend fun getTvShows(@Query("api_key") apiKey: String): Response<TvShowResponse>

}