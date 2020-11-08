package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var Id: String

    private fun getMovies(): List<DataEntity> = DataDummy.generateDummyMovies() as ArrayList<DataEntity>
    private fun getTvShows(): List<DataEntity> = DataDummy.generateDummyTvShows() as ArrayList<DataEntity>

    fun setId(Id: String) {
        this.Id = Id
    }

    fun getDataById(Id: String, Type: String): DataEntity {
        lateinit var result: DataEntity
        if (Type.equals("Movie", ignoreCase = true)) {
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
        return  result
    }
}