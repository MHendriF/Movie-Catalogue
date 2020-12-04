package com.hendri.movie.catalogue.di

import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.network.ApiService
import com.hendri.movie.catalogue.data.source.remote.network.RetrofitBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    internal fun provideApiServiceTheMovieDB(): ApiService {
        return RetrofitBuilder.service(ApiService.BASE_URL, ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(apiServiceTheMovieDB: ApiService): RemoteDataSource {
        return RemoteDataSource.getInstance(apiServiceTheMovieDB)
    }

    @Provides
    @Singleton
    internal fun provideTheMovieDbRepository(remoteDataSource: RemoteDataSource): MainRepository {
        return MainRepository.getInstance(remoteDataSource)
    }
}