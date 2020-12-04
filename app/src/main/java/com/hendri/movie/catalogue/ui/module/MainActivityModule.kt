package com.hendri.movie.catalogue.ui.module

import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.ui.activities.MainActivity
import com.hendri.movie.catalogue.ui.fragments.MovieFragment
import com.hendri.movie.catalogue.ui.fragments.TvShowFragment
import com.hendri.movie.catalogue.ui.viewmodels.MainViewModel
import com.hendri.movie.catalogue.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun homeViewModel(tvViewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeMainActivityModule(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TvShowFragment
}