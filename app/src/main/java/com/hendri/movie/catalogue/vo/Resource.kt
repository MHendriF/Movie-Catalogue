package com.hendri.movie.catalogue.vo

sealed class Resource<out R> {
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Empty<out T>(val data: T? = null) : Resource<T>()
    data class Error<out T>(val message: String, val data: T? = null) : Resource<T>()
}