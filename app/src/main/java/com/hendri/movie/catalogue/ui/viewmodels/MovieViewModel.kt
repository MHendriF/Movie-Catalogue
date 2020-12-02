package com.hendri.movie.catalogue.ui.viewmodels

import androidx.lifecycle.*
import com.hendri.movie.catalogue.data.repository.MainRepository
import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel(private val repository: MainRepository) : ViewModel() {

    private val movies = MutableLiveData<Resource<List<Movie>>>()

    init {
        fetchData()
    }

//    fun getMoviesFromApi() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            val response = repository.getMoviesFromApi()
//            if (response.isSuccessful){
//                emit(Resource.success(data = response.body()?.results))
//            }
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

    private fun fetchData() {
        viewModelScope.launch {
            movies.postValue(Resource.loading(null))
            try {
                val moviesFromApi = repository.getMoviesFromApi()
                if (moviesFromApi.isSuccessful) {
                    movies.postValue(Resource.success(moviesFromApi.body()?.results))
                }
            } catch (e: Exception) {
                movies.postValue(Resource.error(null, e.toString()))
            }
        }
    }

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return movies
    }
}