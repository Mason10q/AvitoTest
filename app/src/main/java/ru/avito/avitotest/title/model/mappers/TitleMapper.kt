package ru.avito.avitotest.title.model.mappers

import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.network.dtos.TitleDto
import ru.avito.avitotest.title.model.entities.Title
import javax.inject.Inject

class TitleMapper @Inject constructor(): Mapper<Title, TitleDto> {

    override fun map(item: TitleDto): Title = Title(
        item.name ?: "",
        item.description ?: ""
    )
}