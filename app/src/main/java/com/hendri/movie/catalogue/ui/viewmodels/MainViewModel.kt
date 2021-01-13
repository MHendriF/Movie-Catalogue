package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.data.repository.MovieRepository
import com.hendri.movie.catalogue.data.repository.TvShowRepository
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val movieRepo: MovieRepository,
    private val tvShowRepo: TvShowRepository
) : ViewModel() {

    val movie = MediatorLiveData<Resource<PagedList<Movie>>>()
    val tvShow = MediatorLiveData<Resource<PagedList<TvShow>>>()
    val movieFavorite = MediatorLiveData<Resource<PagedList<Movie>>>()
    val tvShowFavorite = MediatorLiveData<Resource<PagedList<TvShow>>>()

    init {
        movie.addSource(movieRepo.getResult()) { movie.value = it }
        tvShow.addSource(tvShowRepo.getResult()) { tvShow.value = it }
        movieFavorite.addSource(movieRepo.getFavorite()) { movieFavorite.value = it }
        tvShowFavorite.addSource(tvShowRepo.getFavorite()) { tvShowFavorite.value = it }
    }

    fun sorting(type: Type) {
        when (type) {
            Type.NAME -> {
                movie.addSource(movieRepo.getResult(MovieDao.SORT_BY_NAME)) { movie.value = it }
                tvShow.addSource(tvShowRepo.getResult(TvShowDao.SORT_BY_NAME)) { tvShow.value = it }
                movieFavorite.addSource(movieRepo.getFavorite(MovieDao.SORT_FAV_BY_NAME)) {
                    movieFavorite.value = it
                }
                tvShowFavorite.addSource(tvShowRepo.getFavorite(TvShowDao.SORT_FAV_BY_NAME)) { tvShowFavorite.value = it }
            }
            Type.RELEASE_DATA -> {
                movie.addSource(movieRepo.getResult(MovieDao.SORT_BY_RELEASE)) { movie.value = it }
                tvShow.addSource(tvShowRepo.getResult(TvShowDao.SORT_BY_RELEASE)) { tvShow.value = it }
                movieFavorite.addSource(movieRepo.getFavorite(MovieDao.SORT_FAV_BY_RELEASE)) {
                    movieFavorite.value = it
                }
                tvShowFavorite.addSource(tvShowRepo.getFavorite(TvShowDao.SORT_FAV_BY_RELEASE)) { tvShowFavorite.value = it }
            }
        }
    }

    enum class Type {
        NAME, RELEASE_DATA
    }
}