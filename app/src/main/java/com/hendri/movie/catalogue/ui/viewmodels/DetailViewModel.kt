package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.Resource
import com.hendri.movie.catalogue.data.source.remote.response.MovieDetailResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private lateinit var dataExtra: MutableList<Int>

    var dataMovie: LiveData<Resource<MovieDetailResponse>>? = null
    var dataTv: LiveData<Resource<TvDetailResponse>>? = null

    fun getDataExtra(data: Int) = this.dataExtra[data]

    fun setDataExtra(dataDes: Int, dataId: Int) {
        dataExtra = mutableListOf(dataDes, dataId)
        if (dataTv == null) dataTv = repository.getDataTvById(dataExtra[DATA_ID])
        if (dataMovie == null) dataMovie = repository.getDataMovieById(dataExtra[DATA_ID])
    }

    companion object {
        const val DATA_DESTINATION = 0
        const val DATA_ID = 1
    }
}