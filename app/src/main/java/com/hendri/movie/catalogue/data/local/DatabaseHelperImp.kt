package com.hendri.movie.catalogue.data.local

import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.TvShow

class DatabaseHelperImp(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getMovies(): List<Movie> = appDatabase.movieDao().getAll()

    override suspend fun getTvShows(): List<TvShow> = appDatabase.tvShowDao().getAll()
}