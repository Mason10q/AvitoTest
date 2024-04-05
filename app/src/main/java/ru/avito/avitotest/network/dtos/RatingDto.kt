package ru.avito.avitotest.network.dtos

import com.google.gson.annotations.SerializedName


data class RatingDto (
    @SerializedName("kp")
    val kpRating : Double?
)