package com.hendri.movie.catalogue.data.api

import com.hendri.movie.catalogue.data.response.MovieResponse
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.data.response.TvShowResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getMovies(): Response<MovieResponse>

    suspend fun getTvShows(): Response<TvShowResponse>
}