package com.hendri.movie.catalogue.data.di

import android.app.Application
import com.hendri.movie.catalogue.data.repository.MovieRepository
import com.hendri.movie.catalogue.data.repository.TvShowRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class
    ]
)

interface DataComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): DataComponent
    }

    fun provideMovieRepo(): MovieRepository
    fun provideTvShowRepo(): TvShowRepository
}