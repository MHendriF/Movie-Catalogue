package com.hendri.movie.catalogue.data.repository

import com.hendri.movie.catalogue.data.source.local.LocalDataSource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.utils.Executors

class MainRepository private constructor(
    remote: RemoteDataSource, local: LocalDataSource, executors: Executors
){
    val movie = MovieRepository.getInstance(
        executors = executors, remoteData = remote, localData = local.movie
    )
    val tvShow = TvShowRepository.getInstance(
        executors = executors, remoteData = remote, localData = local.tvShow
    )

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(
            remote: RemoteDataSource, local: LocalDataSource, executors: Executors
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(remote, local, executors)
            }
    }
}