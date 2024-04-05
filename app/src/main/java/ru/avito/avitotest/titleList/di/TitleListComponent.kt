package ru.avito.avitotest.titleList.di

import dagger.Component
import ru.avito.avitotest.core.CoreModule
import ru.avito.avitotest.titleList.ui.TitleListFragment
import ru.avito.avitotest.network.di.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, CoreModule::class, TitleListModule::class])
interface TitleListComponent {

    fun inject(fragment: TitleListFragment)

    @Component.Builder
    interface Builder {
        fun build(): TitleListComponent
    }

}