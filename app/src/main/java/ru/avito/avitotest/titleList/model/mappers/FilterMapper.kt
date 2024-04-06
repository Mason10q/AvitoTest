package ru.avito.avitotest.titleList.model.mappers

import ru.avito.avitotest.core.Mapper
import ru.avito.avitotest.network.dtos.FilterDto
import ru.avito.avitotest.titleList.model.entities.Filter
import javax.inject.Inject

class FilterMapper @Inject constructor(): Mapper<String, FilterDto> {
    override fun map(item: FilterDto): String = item.name ?: ""

}