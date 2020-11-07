package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.ui.repositories.MovieRepository
import com.hendri.movie.catalogue.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovies(): List<DataEntity> = DataDummy.generateDummyMovies()
}