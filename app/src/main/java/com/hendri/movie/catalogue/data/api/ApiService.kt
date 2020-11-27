package com.hendri.movie.catalogue.data.api

import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.MovieResponse
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.data.response.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<TvShowResponse>

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<Movie>

    @GET("tv/{id}")
    suspend fun getTvShowById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<TvShow>
}