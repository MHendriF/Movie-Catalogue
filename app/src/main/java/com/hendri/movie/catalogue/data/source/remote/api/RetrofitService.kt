package com.hendri.movie.catalogue.data.source.remote.api

import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MoviesResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<MoviesResponse>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<TvShowsResponse>

    @GET("movie/{id}")
    fun getMovieById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("tv/{id}")
    fun getTvShowById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

}