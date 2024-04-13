package ru.avito.avitotest.data.network.dtos

import com.google.gson.annotations.SerializedName


data class RatingDto (
    @SerializedName("kp")
    val kpRating : Double?
)