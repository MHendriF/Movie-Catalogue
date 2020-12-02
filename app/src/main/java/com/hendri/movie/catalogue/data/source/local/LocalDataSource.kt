package com.hendri.movie.catalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.hendri.movie.catalogue.data.source.local.dao.FavoriteDao
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao
import com.hendri.movie.catalogue.data.source.local.entity.Favorite
import com.hendri.movie.catalogue.data.source.local.entity.Movie
import com.hendri.movie.catalogue.data.source.local.entity.TvShow

class LocalDataSource private constructor(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
    private val favoriteDao: FavoriteDao
) {
    companion object {
        private val instance: LocalDataSource? = null
        fun getInstance(
            movieDao: MovieDao,
            tvShowDao: TvShowDao,
            favoriteDao: FavoriteDao
        ): LocalDataSource =
            instance ?: getInstance(movieDao, tvShowDao, favoriteDao)
    }

    fun getMovies(): LiveData<List<Movie>> = movieDao.getMovies()
    fun getMovieById(id: Int): LiveData<Movie> = movieDao.getMovieById(id)
    fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)
    fun updateMovie(movie: Movie) = movieDao.updateMovie(movie)

    //TvShow
    fun getTvShows(): LiveData<List<TvShow>> = tvShowDao.getTvShows()
    fun getTvShowById(id: Int): LiveData<TvShow> = tvShowDao.getTvShowDetail(id)
    fun insertTvShows(tvShows: List<TvShow>) = tvShowDao.insertTvShows(tvShows)
    fun updateTvShow(tvShow: TvShow) = tvShowDao.updateTvShow(tvShow)

    //Favorite
    fun getFavoriteByType(type: String): DataSource.Factory<Int, Favorite> = favoriteDao.getFavoriteByType(type)
    fun insertFavorite(item: Favorite) = favoriteDao.insertFavorite(item)
    fun deleteFavorite(item: Favorite) = favoriteDao.deleteFavorite(item)
    fun getFavoriteById(id: Int): LiveData<Favorite> = favoriteDao.getFavoriteById(id)
}