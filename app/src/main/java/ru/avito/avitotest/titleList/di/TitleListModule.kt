package ru.avito.avitotest.titleList.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.avito.avitotest.core.di.ViewModelKey
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.TitleListRepository
import ru.avito.avitotest.data.TitleListRepositoryImpl
import ru.avito.avitotest.titleList.model.TitleListUseCaseImpl
import ru.avito.avitotest.titleList.model.mappers.PreviewTitleMapper
import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.titleList.ui.TitleListViewModel
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.titleList.model.FilterUseCase
import ru.avito.avitotest.titleList.model.FilterUseCaseImpl
import ru.avito.avitotest.titleList.model.TitleListUseCase

@Module
interface TitleListModule {

    @Binds
    fun bindTitleListRepository(repository: TitleListRepositoryImpl): TitleListRepository

    @Binds
    fun bindPreviewTitleMapper(mapper: PreviewTitleMapper): Mapper<Title, TitleDto>

    @Binds
    fun bindTitleListUseCase(useCase: TitleListUseCaseImpl): TitleListUseCase

    @Binds
    fun bindFilterUseCase(useCase: FilterUseCaseImpl): FilterUseCase


    @Binds
    @IntoMap
    @ViewModelKey(TitleListViewModel::class)
    fun bindTitleListViewModel(viewModel: TitleListViewModel): ViewModel
}