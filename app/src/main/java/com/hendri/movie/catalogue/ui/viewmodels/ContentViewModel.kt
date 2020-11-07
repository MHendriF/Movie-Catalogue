package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.utils.DataDummy

class ContentViewModel : ViewModel() {

    fun getMovies(): List<DataEntity> = DataDummy.generateDummyMovies()

    fun getTvShows(): List<DataEntity> = DataDummy.generateDummyTvShows()
}