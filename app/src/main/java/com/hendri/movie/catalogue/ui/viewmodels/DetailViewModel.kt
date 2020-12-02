package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.local.entity.Favorite
import com.hendri.movie.catalogue.data.source.local.entity.Movie
import com.hendri.movie.catalogue.data.source.local.entity.TvShow
import com.hendri.movie.catalogue.data.source.remote.vo.Resource

class DetailViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getMovieById(id: Int): LiveData<Resource<Movie>> = mainRepository.getMovieById(id)

    fun getTvShowById(id: Int): LiveData<Resource<TvShow>> = mainRepository.getTvShowById(id)

    fun insertFavorite(item: Favorite) = mainRepository.insertFavorite(item)

    fun deleteFavorite(item: Favorite) = mainRepository.deleteFavorite(item)

    fun getFavoriteById(id: Int): LiveData<Favorite> = mainRepository.getFavoriteById(id)
}