package com.hendri.movie.catalogue.data.source.local

import com.hendri.movie.catalogue.data.source.local.dao.MovieDao
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao

class LocalDataSource private constructor(movieDao: MovieDao, tvShowDao: TvShowDao) {

    val movie = MovieDataSource.getInstance(movieDao)
    val tvShow = TvShowDataSource.getInstance(tvShowDao)

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao, tvShowDao: TvShowDao): LocalDataSource =
            instance ?: synchronized(this) { instance ?: LocalDataSource(movieDao, tvShowDao) }
    }
}