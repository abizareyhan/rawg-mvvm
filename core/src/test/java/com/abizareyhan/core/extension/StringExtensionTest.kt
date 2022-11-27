package com.abizareyhan.core.extension

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.time.Month

class StringExtensionTest {
    @Test
    fun `parseToLocalDate from correct format`() {
        val dateString = "2022-01-31"

        val localDate = dateString.parseToLocalDate()

        assertNotNull(localDate)
        assertEquals(2022, localDate?.year)
        assertEquals(Month.JANUARY, localDate?.month, )
        assertEquals(31, localDate?.dayOfMonth)
        assertEquals(LocalDate.of(2022, Month.JANUARY, 31), localDate)
    }

    @Test
    fun `parseToLocalDate from incorrect format`() {
        val dateString = "31-01-2022"
        val localDate = dateString.parseToLocalDate()

        assertNull(localDate)
    }

    @Test
    fun `parseToLocalDate from empty string`() {
        val dateString = ""
        val localDate = dateString.parseToLocalDate()

        assertNull(localDate)
    }

    @Test
    fun `parseToLocalDate from random string`() {
        val dateString = "idontknowwhattowritehere"
        val localDate = dateString.parseToLocalDate()

        assertNull(localDate)
    }
}