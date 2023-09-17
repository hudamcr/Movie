package com.huda.movie.presentation.utils

fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun String?.orDash() = this ?: "-"

fun String?.orTrailingDots() = this ?: "..."

