package ru.avito.avitotest.title.model.mappers

import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.data.network.dtos.PosterDto
import ru.avito.avitotest.title.model.entities.Poster
import javax.inject.Inject

class PosterMapper @Inject constructor(): Mapper<Poster, PosterDto> {
    override fun map(item: PosterDto): Poster = Poster(
        item.url ?: ""
    )
}