package com.hendri.movie.catalogue.di

import android.content.Context
import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.local.AppDatabase
import com.hendri.movie.catalogue.data.source.local.LocalDataSource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.api.RetrofitClient
import com.hendri.movie.catalogue.utils.AppExecutors

object Injection {
    fun provideInjection(context: Context): MainRepository {
        val client = RetrofitClient
        val database = AppDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(client)
        val localDataSource = LocalDataSource.getInstance(database.movieDao(), database.tvShowDao(), database.favoriteDao())
        val appExecutors = AppExecutors()
        return MainRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}