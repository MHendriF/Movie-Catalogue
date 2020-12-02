package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.local.entity.Movie
import com.hendri.movie.catalogue.data.source.remote.vo.Resource

class MovieViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<Movie>>> = mainRepository.getMovies()
}