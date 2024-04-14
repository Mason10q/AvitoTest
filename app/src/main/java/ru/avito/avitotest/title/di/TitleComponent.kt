package ru.avito.avitotest.title.di

import dagger.Component
import ru.avito.avitotest.core.di.AndroidModule
import ru.avito.avitotest.core.di.CoreModule
import ru.avito.avitotest.data.network.di.NetworkModule
import ru.avito.avitotest.title.ui.ReviewsFragment
import ru.avito.avitotest.title.ui.TitleFragment
import ru.avito.avitotest.titleList.di.TitleListComponent
import ru.avito.avitotest.titleList.di.TitleListModule
import ru.avito.avitotest.titleList.ui.adapters.TitleListAdapter
import ru.avito.avitotest.titleList.ui.fragments.FilterDialog
import ru.avito.avitotest.titleList.ui.fragments.TitleListFragment
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, CoreModule::class, TitleModule::class])
interface TitleComponent {

    fun inject(fragment: TitleFragment)

    fun inject(fragment: ReviewsFragment)

    @Component.Builder
    interface Builder {
        fun build(): TitleComponent
    }

}