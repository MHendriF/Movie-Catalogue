package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.local.entity.TvShow
import com.hendri.movie.catalogue.data.source.remote.vo.Resource

class TvShowViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getTvShows(): LiveData<Resource<List<TvShow>>> = mainRepository.getTvShows()
}