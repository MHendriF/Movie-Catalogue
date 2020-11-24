package com.hendri.movie.catalogue.data.local

import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.TvShow

interface DatabaseHelper {
    suspend fun getMovies(): List<Movie>

    suspend fun getTvShows(): List<TvShow>
}