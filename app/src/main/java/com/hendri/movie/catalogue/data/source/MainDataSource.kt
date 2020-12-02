package com.hendri.movie.catalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hendri.movie.catalogue.data.source.local.entity.Favorite
import com.hendri.movie.catalogue.data.source.local.entity.Movie
import com.hendri.movie.catalogue.data.source.local.entity.TvShow
import com.hendri.movie.catalogue.data.source.remote.vo.Resource

interface MainDataSource {
    fun getMovies(): LiveData<Resource<List<Movie>>>

    fun getTvShows(): LiveData<Resource<List<TvShow>>>

    fun getMovieById(id: Int): LiveData<Resource<Movie>>

    fun getTvShowById(id: Int): LiveData<Resource<TvShow>>

    fun getFavoriteByType(type: String): LiveData<PagedList<Favorite>>

    fun insertFavorite(item: Favorite)

    fun deleteFavorite(item: Favorite)

    fun getFavoriteById(id: Int): LiveData<Favorite>
}