package ru.avito.avitotest.core

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface CoreModule {

    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

}