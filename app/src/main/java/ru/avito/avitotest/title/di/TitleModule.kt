package ru.avito.avitotest.title.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.core.di.ViewModelKey
import ru.avito.avitotest.data.TitleListRepository
import ru.avito.avitotest.data.TitleListRepositoryImpl
import ru.avito.avitotest.data.network.dtos.PosterDto
import ru.avito.avitotest.data.network.dtos.ReviewDto
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.title.model.TitleUseCase
import ru.avito.avitotest.title.model.TitleUseCaseImpl
import ru.avito.avitotest.title.model.entities.Poster
import ru.avito.avitotest.title.model.entities.Review
import ru.avito.avitotest.title.model.entities.Title
import ru.avito.avitotest.title.model.mappers.PosterMapper
import ru.avito.avitotest.title.model.mappers.ReviewMapper
import ru.avito.avitotest.title.model.mappers.TitleMapper
import ru.avito.avitotest.title.ui.TitleViewModel
import ru.avito.avitotest.titleList.ui.TitleListViewModel

@Module
interface TitleModule {

    @Binds
    fun bindTitleListRepository(repository: TitleListRepositoryImpl): TitleListRepository
    @Binds
    fun bindPosterMapper(mapper: PosterMapper): Mapper<Poster, PosterDto>

    @Binds
    fun bindReviewMapper(mapper: ReviewMapper): Mapper<Review, ReviewDto>

    @Binds
    fun bindTitleMapper(mapper: TitleMapper): Mapper<Title, TitleDto>

    @Binds
    fun bindTitleUseCase(useCase: TitleUseCaseImpl): TitleUseCase


    @Binds
    @IntoMap
    @ViewModelKey(TitleViewModel::class)
    fun bindTitleViewModel(viewModel: TitleViewModel): ViewModel

}