package com.hendri.movie.catalogue.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.utils.DataDummy

class MovieRepository {
    fun getMovies() : LiveData <List<DataEntity>> {
        var data: MutableLiveData<List<DataEntity>> = MutableLiveData<List<DataEntity>>()
        data.value = DataDummy.generateDummyMovies()
        return data
    }
}