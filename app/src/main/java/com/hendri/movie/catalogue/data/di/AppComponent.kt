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
        AppModule::class
    ]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun provideMovieRepo(): MovieRepository
    fun provideTvShowRepo(): TvShowRepository
}