package com.example.resmedsports.di.component

import android.app.Application
import com.example.resmedsports.BaseApplication
import com.example.resmedsports.di.module.ActivitiesModule
import com.example.resmedsports.di.module.ViewModelsModule
import com.example.resmedsports.di.module.ViewModuleFactoryModule
import com.example.resmedsports.di.module.WebServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *  AppComponent exist for the life time of application. It has the list of modules which are going to be used by application.
 */
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModuleFactoryModule::class,
        WebServiceModule::class,
        ActivitiesModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}