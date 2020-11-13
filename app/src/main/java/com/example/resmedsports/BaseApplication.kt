package com.example.resmedsports

import com.example.resmedsports.di.component.DaggerAppComponent
import com.example.resmedsports.util.TimberLineNumberDebugTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class BaseApplication : DaggerApplication() {
    @Inject
    lateinit var timberLineNumberDebugTree: TimberLineNumberDebugTree

    override fun onCreate() {
        super.onCreate()

        Timber.plant(timberLineNumberDebugTree)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}