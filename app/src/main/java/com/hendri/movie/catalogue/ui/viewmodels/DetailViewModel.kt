package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.utils.Constants
import com.hendri.movie.catalogue.utils.DataDummy

class DetailViewModel : ViewModel() {

    private fun getMovies(): List<DataEntity> = ArrayList(DataDummy.generateDummyMovies())

    private fun getTvShows(): List<DataEntity> =  ArrayList(DataDummy.generateDummyTvShows())

    fun getDataById(Id: String?, Type: String?): DataEntity {
        lateinit var result: DataEntity
        if (Type.equals(Constants.TYPE_MOVIE, ignoreCase = true)) {
            val listMovie = getMovies()
            for (movie in listMovie) {
                if (movie.id == Id) {
                    result = movie
                    break
                }
            }
        } else {
            val listTvShow = getTvShows()
            for (tvShow in listTvShow) {
                if (tvShow.id == Id) {
                    result = tvShow
                    break
                }
            }
        }
        return result
    }
}