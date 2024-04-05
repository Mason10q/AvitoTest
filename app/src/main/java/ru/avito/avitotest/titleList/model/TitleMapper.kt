package ru.avito.avitotest.titleList.model

import ru.avito.avitotest.titleList.model.entities.Title
import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.network.dtos.TitleDto
import javax.inject.Inject

class TitleMapper @Inject constructor(): Mapper<Title, TitleDto> {

    override fun map(item: TitleDto): Title =
        Title(
            item.name ?: "",
            item.alternativeName ?: "",
            item.year ?: 1970,
            item.counties?.get(0)?.name ?: "",
            item.genre ?: "",
            item.rating ?: 0.0f
        )
}