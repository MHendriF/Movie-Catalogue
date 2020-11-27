package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hendri.movie.catalogue.data.repository.MainRepository
import com.hendri.movie.catalogue.utils.Resource
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private val repository: MainRepository) : ViewModel() {

    fun getMovieFromApi(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = repository.getMovieByIdFromApi(id)
            if (response.isSuccessful){
                emit(Resource.success(data = response.body()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getTvShowFromApi(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = repository.getTvShowByIdFromApi(id)
            if (response.isSuccessful){
                emit(Resource.success(data = response.body()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}