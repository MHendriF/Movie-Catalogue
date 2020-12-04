package com.hendri.movie.catalogue

import android.app.Application
import com.hendri.movie.catalogue.di.DaggerAppComponent
import com.hendri.movie.catalogue.utils.logging.ReleaseTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MyApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }
}