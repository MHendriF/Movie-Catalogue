package com.hendri.movie.catalogue.data.repository

import com.hendri.movie.catalogue.data.api.ApiHelper
import com.hendri.movie.catalogue.data.local.DatabaseHelper
import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

class MovieRepository(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
) {

    suspend fun getMoviesFromApi(): Response<MovieResponse> {
        Timber.d("Trace :: getMoviesFromApi")
        return withContext(Dispatchers.IO) {
            apiHelper.getMovies()
        }
    }

    suspend fun getMoviesFromDB(): List<Movie> {
        return dbHelper.getMovies()
    }
}