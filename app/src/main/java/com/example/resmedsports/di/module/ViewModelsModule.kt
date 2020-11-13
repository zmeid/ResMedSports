package com.example.resmedsports.di.module

import androidx.lifecycle.ViewModel
import com.example.resmedsports.di.ViewModelKey
import com.example.resmedsports.viewmodel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Enables dependency injection for viewModels. New viewModels should be added here with related activity/fragment.
 */
@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}