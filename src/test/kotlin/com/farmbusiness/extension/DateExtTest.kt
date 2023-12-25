package com.farmbusiness.extension

import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class DateExtTest {

    @Test
    fun `is same day extension function is true`() {
        val day1 = Date()
        val day2 = Date()

        val result = day1.isSameDay(day2)

        assertEquals(true, result)
    }

    @Test
    fun `is same day extension function is false for different days`() {
        val today = Date()
        val day1 = today
        val day2 = today.add(Calendar.DAY_OF_MONTH, 1)

        val result = day1.isSameDay(day2)

        assertEquals(false, result)
    }

    @Test
    fun `is same day extension function is false for different month`() {
        val today = Date()
        val day1 = today
        val day2 = today.add(Calendar.MONTH, 1)

        val result = day1.isSameDay(day2)

        assertEquals(false, result)
    }

    @Test
    fun `is same day extension function is false for different year`() {
        val today = Date()
        val day1 = today
        val day2 = today.add(Calendar.YEAR, 1)

        val result = day1.isSameDay(day2)

        assertEquals(false, result)
    }

    @Test
    fun `is same day extension function is false for different hour when include`() {
        val today = Date()
        val day1 = today
        val day2 = today.add(Calendar.HOUR, 1)

        val result = day1.isSameDay(day2, true)

        assertEquals(false, result)
    }

    @Test
    fun `set Year for a Date type`() {
        val date = Date().set(year = 2000)

        assertEquals(2000, date.year())
    }


    @Test
    fun `get BR format with seconds`() {
        val day = Date().set(
            year = 2000,
            month = 10,
            day = 20,
            hour = 10,
            minute = 10,
            second = 30
        )
        val expected = "20/10/2000 10:10:30"

        val result = day.toBRFormat()

        assertEquals(expected, result)
    }

    @Test
    fun `is same day extension function is false for different minute when include`() {
        val today = Date()
        val day1 = today
        val day2 = today.add(Calendar.MINUTE, 1)

        val result = day1.isSameDay(day2, true)

        assertEquals(false, result)
    }


    @Test
    fun `get BR format without seconds`() {
        val day = Date().set(
            year = 2000,
            month = 10,
            day = 20,
            hour = 10,
            minute = 10,
            second = 0
        )
        val expected = "20/10/2000 10:10:00"

        val result = day.toBRFormat()

        assertEquals(expected, result)
    }
}