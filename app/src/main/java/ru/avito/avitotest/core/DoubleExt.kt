package ru.avito.avitotest.core

import kotlin.math.pow
import kotlin.math.round

fun Double.round(amount: Int) = (round(this * 10.0.pow(amount)) / 10.0.pow(amount))