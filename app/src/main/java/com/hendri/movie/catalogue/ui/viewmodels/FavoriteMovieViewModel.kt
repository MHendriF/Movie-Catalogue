package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.local.entity.Favorite

class FavoriteMovieViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getFavorite(type: String): LiveData<PagedList<Favorite>> = mainRepository.getFavoriteByType(type)
}