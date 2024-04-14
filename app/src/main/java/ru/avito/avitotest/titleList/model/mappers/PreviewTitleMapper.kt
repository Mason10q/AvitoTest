package ru.avito.avitotest.titleList.model.mappers

import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.core.round
import ru.avito.avitotest.data.network.dtos.TitleDto
import javax.inject.Inject

class PreviewTitleMapper @Inject constructor(): Mapper<Title, TitleDto> {

    override fun map(item: TitleDto): Title =
        Title(
            item.id ?: 0,
            item.name ?: "",
            item.alternativeName ?: "",
            item.year ?: 1970,
            item.counties?.get(0)?.name ?: "",
            item.genres?.get(0)?.name ?: "",
            item.rating?.kpRating?.round(2) ?: 0.0,
            item.posterDto?.previewUrl ?: ""
        )
}