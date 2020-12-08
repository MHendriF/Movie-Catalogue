package com.hendri.movie.catalogue.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao.Companion.SORT_BY_NAME
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao.Companion.SORT_FAV_BY_NAME
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieRelation
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieWithGenre
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieResponseWithGenre
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse

class MovieDataSource private constructor(private val movieDao: MovieDao) :
    IMainDataSource<MovieResponse, DetailMovieResponse, MovieResponseWithGenre, MovieWithGenre, DetailMovieRelation> {

    companion object {
        @Volatile
        private var instance: MovieDataSource? = null

        fun getInstance(movieDao: MovieDao): MovieDataSource =
            instance ?: synchronized(this) { instance ?: MovieDataSource(movieDao) }
    }

    override fun getResponse() = movieDao.liveMovie()

    override fun getResult() = movieDao.pageMovie()

    override fun getResultRawQuery(supportSQLiteQuery: SupportSQLiteQuery?) =
        movieDao.pageMovie(supportSQLiteQuery ?: run { SORT_BY_NAME })

    override fun getDetail(id: Int) = movieDao.liveDetailMovie(id)

    override fun insertResponse(data: MovieResponse) = movieDao.insertMovie(data)

    override fun insertDetailResponse(data: DetailMovieResponse) = movieDao.insertMovieDetail(data)

    override fun updateFavorite(id: Int, isFavorite: Boolean) {
        movieDao.updateDetailFavorite(movieDao.detailMovie(id).apply { this.isFavorite = isFavorite })
        movieDao.updateResultFavorite(movieDao.movie(id).apply { this.isFavorite = isFavorite })
    }

    override fun getFavorite(supportSQLiteQuery: SupportSQLiteQuery?) =
        movieDao.pageMovieFavorite(supportSQLiteQuery ?: run { SORT_FAV_BY_NAME })
}