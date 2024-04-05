package ru.avito.avitotest.core.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.avito.avitotest.core.ViewModelFactory

@Module
interface CoreModule {

    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

}