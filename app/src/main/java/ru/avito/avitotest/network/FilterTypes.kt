package ru.avito.avitotest.network

import ru.avito.avitotest.R

enum class FilterTypes(val title: String, val serverName: String) {
    GENRES("Жанры", "genres.name"),
    TYPES("Виды", "type"),
    COUNTRIES("Страны", "countries.name"),
    YEARS("Годы", "year"),
    AGE_RATING("Возрастные ограничения", "ageRating"),
    RATING("Рейтинг", "rating.kp"),
    MOVIE_LENGTH("Длина фильма", "movieLength")
}