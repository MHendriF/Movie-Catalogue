package com.hendri.movie.catalogue.data.di

import android.app.Application
import com.hendri.movie.catalogue.data.repository.MainRepository
import com.hendri.movie.catalogue.data.repository.MovieRepository
import com.hendri.movie.catalogue.data.repository.TvShowRepository
import com.hendri.movie.catalogue.data.source.local.LocalDataSource
import com.hendri.movie.catalogue.data.source.local.room.AppDatabase
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.network.ApiService
import com.hendri.movie.catalogue.data.source.remote.network.RetrofitBuilder
import com.hendri.movie.catalogue.utils.Constants
import com.hendri.movie.catalogue.utils.Executors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    internal fun provideApiServiceTheMovie() =
        RetrofitBuilder.service(Constants.TMDB_BASE_URL, ApiService::class.java)

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(apiService: ApiService) =
        RemoteDataSource.getInstance(apiService)

    @Provides
    @Singleton
    internal fun provideTheMovieDatabase(application: Application) =
        AppDatabase.getDatabase(application)

    @Provides
    @Singleton
    internal fun provideLocalDataSource(database: AppDatabase) =
        LocalDataSource.getInstance(database.movieDao(), database.tvShowDao())

    @Provides
    @Singleton
    internal fun provideTheMovieRepository(
        remote: RemoteDataSource, local: LocalDataSource
    ) = MainRepository.getInstance(remote, local, Executors())

    @Provides
    @Singleton
    internal fun provideMovieRepository(
        remote: RemoteDataSource, local: LocalDataSource
    ) = MovieRepository.getInstance(Executors(), remote, local.movie)

    @Provides
    @Singleton
    internal fun provideTvRepository(
        remote: RemoteDataSource, local: LocalDataSource
    ) = TvShowRepository.getInstance(Executors(), remote, local.tvShow)

}