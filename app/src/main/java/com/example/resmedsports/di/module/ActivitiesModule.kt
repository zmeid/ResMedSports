package com.example.resmedsports.di.module

import com.example.resmedsports.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule{
    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}