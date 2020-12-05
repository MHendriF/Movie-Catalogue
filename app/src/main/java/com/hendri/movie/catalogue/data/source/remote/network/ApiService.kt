package com.hendri.movie.catalogue.data.source.remote.network

import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?api_key=${Constants.TMDB_API_KEY}")
    fun getDataMovie(
        @Query("language") language: String? = "en-US",
    ): Call<MovieResponse>?

    @GET("discover/tv?api_key=${Constants.TMDB_API_KEY}")
    fun getDataTv(
        @Query("language") language: String? = "en-US",
    ): Call<TvShowResponse>?

    @GET("movie/{id}?api_key=${Constants.TMDB_API_KEY}")
    fun getDataMovieById(
        @Path("id") id: Int,
        @Query("language") language: String? = "en-US",
    ): Call<DetailMovieResponse>

    @GET("tv/{id}?api_key=${Constants.TMDB_API_KEY}")
    fun getDataTvById(
        @Path("id") id: Int,
        @Query("language") language: String? = "en-US",
    ): Call<DetailTvShowResponse>
}