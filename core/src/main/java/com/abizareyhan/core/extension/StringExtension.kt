package com.abizareyhan.core.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.parseToLocalDate(): LocalDate? {
    return try {
        LocalDate.parse(
            this,
            DateTimeFormatter.ofPattern("y-MM-d")
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}