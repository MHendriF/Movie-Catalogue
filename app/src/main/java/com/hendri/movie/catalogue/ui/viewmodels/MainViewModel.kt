package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.source.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: MainRepository) : ViewModel() {
    val getMovies by lazy { repository.getMovies() }
    val getTvShows by lazy { repository.getTvShows() }
}