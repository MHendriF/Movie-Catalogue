package com.hendri.movie.catalogue.data.api

import com.hendri.movie.catalogue.data.response.MovieResponse
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.data.response.TvShowResponse
import com.hendri.movie.catalogue.utils.Constants
import retrofit2.Response

class ApiHelperImp(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMovies(): Response<MovieResponse> = apiService.getMovies(apiKey = Constants.TMDB_API_KEY)

    override suspend fun getTvShows(): Response<TvShowResponse> = apiService.getTvShows(apiKey = Constants.TMDB_API_KEY)
}