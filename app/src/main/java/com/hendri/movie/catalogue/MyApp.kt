package com.hendri.movie.catalogue

import android.app.Application
import com.hendri.movie.catalogue.data.di.DaggerDataComponent
import com.hendri.movie.catalogue.data.di.DataComponent
import com.hendri.movie.catalogue.di.AppComponent
import com.hendri.movie.catalogue.di.DaggerAppComponent
import com.hendri.movie.catalogue.utils.ReleaseTree
import timber.log.Timber

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    private val dataComponent: DataComponent by lazy {
        DaggerDataComponent.factory().create(this)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(dataComponent)
    }
}