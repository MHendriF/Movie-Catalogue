package com.hendri.movie.catalogue.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.data.api.ApiHelper
import com.hendri.movie.catalogue.data.local.DatabaseHelper
import com.hendri.movie.catalogue.data.repository.MainRepository
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel
import com.hendri.movie.catalogue.ui.viewmodels.MovieViewModel
import com.hendri.movie.catalogue.ui.viewmodels.TvShowViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(MainRepository(apiHelper, dbHelper)) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(MainRepository(apiHelper, dbHelper)) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(MainRepository(apiHelper, dbHelper)) as T
            }
        }
        throw IllegalArgumentException("Unknown class name")
    }
}