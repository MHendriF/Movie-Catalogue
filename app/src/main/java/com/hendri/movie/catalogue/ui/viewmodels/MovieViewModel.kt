package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hendri.movie.catalogue.data.repository.MainRepository
import com.hendri.movie.catalogue.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repository: MainRepository) : ViewModel() {
    fun getMoviesFromApi() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = repository.getMoviesFromApi()
            if (response.isSuccessful){
                emit(Resource.success(data = response.body()?.results))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}