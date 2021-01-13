package com.hendri.movie.catalogue.data.source.remote.network

import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key=${Constants.TMDB_API_KEY}")
    fun getMovies(): Call<MovieResponse>?

    @GET("discover/tv?api_key=${Constants.TMDB_API_KEY}")
    fun getTvShows(): Call<TvShowResponse>?

    @GET("movie/{id}?api_key=${Constants.TMDB_API_KEY}")
    fun getMovieById(
        @Path("id") id: Int,
    ): Call<DetailMovieResponse>

    @GET("tv/{id}?api_key=${Constants.TMDB_API_KEY}")
    fun getTvShowById(
        @Path("id") id: Int,
    ): Call<DetailTvShowResponse>
}