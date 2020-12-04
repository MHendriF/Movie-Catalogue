package com.hendri.movie.catalogue.ui.module

import androidx.lifecycle.ViewModel
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.fragments.DetailMovieFragment
import com.hendri.movie.catalogue.ui.fragments.DetailTvShowFragment
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel
import com.hendri.movie.catalogue.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    protected abstract fun detailViewModel(detailViewModel: DetailViewModel): ViewModel

    @ContributesAndroidInjector()
    abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailMovieFragment(): DetailMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailTvShowFragment(): DetailTvShowFragment
}