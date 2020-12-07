package com.hendri.movie.catalogue.di

import com.hendri.movie.catalogue.data.di.DataComponent
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.activities.MainActivity
import com.hendri.movie.catalogue.ui.fragments.*
import com.hendri.movie.catalogue.viewmodel.ViewModelModule
import dagger.Component

@AppScope
@Component(
    dependencies = [DataComponent::class],
    modules = [
        ViewModelModule::class,
    ]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(dataComponent: DataComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvShowFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvShowFragment)
}