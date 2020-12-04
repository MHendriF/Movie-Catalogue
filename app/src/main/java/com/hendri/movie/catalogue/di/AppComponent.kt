package com.hendri.movie.catalogue.di

import android.app.Application
import com.hendri.movie.catalogue.MyApp
import com.hendri.movie.catalogue.ui.module.DetailActivityModule
import com.hendri.movie.catalogue.ui.module.MainActivityModule
import com.hendri.movie.catalogue.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        ViewModelFactoryModule::class,
        MainActivityModule::class,
        DetailActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)

@Singleton
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}