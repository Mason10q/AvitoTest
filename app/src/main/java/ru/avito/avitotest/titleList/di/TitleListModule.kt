package ru.avito.avitotest.titleList.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.avito.avitotest.core.ViewModelKey
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.titleList.data.TitleListRepository
import ru.avito.avitotest.titleList.data.TitleListRepositoryImpl
import ru.avito.avitotest.titleList.model.TitleListUseCaseImpl
import ru.avito.avitotest.titleList.model.TitleMapper
import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.titleList.ui.TitleListViewModel
import ru.avito.avitotest.network.dtos.TitleDto
import ru.avito.avitotest.titleList.model.TitleListUseCase

@Module
interface TitleListModule {

    @Binds
    fun bindTitleListRepository(repository: TitleListRepositoryImpl): TitleListRepository

    @Binds
    fun bindTitleMapper(mapper: TitleMapper): Mapper<Title, TitleDto>

    @Binds
    fun bindTitleListUseCase(useCase: TitleListUseCaseImpl): TitleListUseCase

    @Binds
    @IntoMap
    @ViewModelKey(TitleListViewModel::class)
    fun bindTitleListViewModel(viewModel: TitleListViewModel): ViewModel
}