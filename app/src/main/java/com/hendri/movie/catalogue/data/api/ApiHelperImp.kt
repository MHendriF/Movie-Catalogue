package com.hendri.movie.catalogue.data.api

import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.MovieResponse
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.data.response.TvShowResponse
import com.hendri.movie.catalogue.utils.Constants
import retrofit2.Response

class ApiHelperImp(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMovies(): Response<MovieResponse> = apiService.getMovies(apiKey = Constants.TMDB_API_KEY, page = 1)

    override suspend fun getTvShows(): Response<TvShowResponse> = apiService.getTvShows(apiKey = Constants.TMDB_API_KEY, page = 1)

    override suspend fun getMovieById(id: Int): Response<Movie> = apiService.getMovieById(id, apiKey = Constants.TMDB_API_KEY)

    override suspend fun getTvShowById(id: Int): Response<TvShow> = apiService.getTvShowById(id, apiKey = Constants.TMDB_API_KEY)
}