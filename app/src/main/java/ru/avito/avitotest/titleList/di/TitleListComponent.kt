package ru.avito.avitotest.titleList.di

import dagger.Component
import ru.avito.avitotest.core.di.AndroidModule
import ru.avito.avitotest.core.di.CoreModule
import ru.avito.avitotest.titleList.ui.TitleListFragment
import ru.avito.avitotest.network.di.NetworkModule
import ru.avito.avitotest.titleList.ui.TitleListAdapter
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, CoreModule::class, TitleListModule::class, AndroidModule::class])
interface TitleListComponent {

    fun inject(fragment: TitleListFragment)

    fun inject(adapter: TitleListAdapter)

    @Component.Builder
    interface Builder {
        fun build(): TitleListComponent

        fun androidModule(androidModule: AndroidModule): Builder
    }

}