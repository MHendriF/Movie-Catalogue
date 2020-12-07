package com.hendri.movie.catalogue.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao.Companion.SORT_BY_NAME
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao.Companion.SORT_FAV_BY_NAME
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieRelation
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieGenreRelation
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieResponseRelation
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse

class MovieDataSource private constructor(private val movieDao: MovieDao) :
    IMainDataSource<MovieResponse, DetailMovieResponse, MovieResponseRelation, MovieGenreRelation, DetailMovieRelation> {

    companion object {
        @Volatile
        private var instance: MovieDataSource? = null

        fun getInstance(movieDao: MovieDao): MovieDataSource =
            instance ?: synchronized(this) { instance ?: MovieDataSource(movieDao) }
    }

    override fun getResponse() = movieDao.liveResponse()

    override fun getResult() = movieDao.pageResult()

    override fun getResultRawQuery(supportSQLiteQuery: SupportSQLiteQuery?) =
        movieDao.pageResult(supportSQLiteQuery ?: run { SORT_BY_NAME })

    override fun getDetail(id: Int) = movieDao.liveMovieDetail(id)

    override fun insertResponse(data: MovieResponse) = movieDao.insertMovie(data)

    override fun insertDetailResponse(data: DetailMovieResponse) = movieDao.insertMovieDetail(data)

    override fun updateFavorite(id: Int, isFavorite: Boolean) {
        movieDao.updateDetailFavorite(movieDao.movieDetail(id).apply { this.isFavorite = isFavorite })
        movieDao.updateResultFavorite(movieDao.movieResult(id).apply { this.isFavorite = isFavorite })
    }

    override fun getFavorite(supportSQLiteQuery: SupportSQLiteQuery?) =
        movieDao.pageMovieFavorite(supportSQLiteQuery ?: run { SORT_FAV_BY_NAME })
}