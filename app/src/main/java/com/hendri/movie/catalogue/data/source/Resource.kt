package com.hendri.movie.catalogue.data.source

sealed class Resource<out R> {
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
    data class Empty<out T>(val data: T? = null) : Resource<T>()
}