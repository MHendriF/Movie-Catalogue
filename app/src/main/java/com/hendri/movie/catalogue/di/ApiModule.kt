package com.hendri.movie.catalogue.di

import com.hendri.movie.catalogue.data.source.MainRepository
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.network.ApiService
import com.hendri.movie.catalogue.data.source.remote.network.RetrofitBuilder
import com.hendri.movie.catalogue.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    internal fun provideApiService(): ApiService {
        return RetrofitBuilder.service(Constants.TMDB_BASE_URL, ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource.getInstance(apiService)
    }

    @Provides
    @Singleton
    internal fun provideMainRepository(remoteDataSource: RemoteDataSource): MainRepository {
        return MainRepository.getInstance(remoteDataSource)
    }
}