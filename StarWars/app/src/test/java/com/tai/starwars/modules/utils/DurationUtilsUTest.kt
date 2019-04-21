package com.tai.starwars.modules.utils

import org.junit.Assert.*
import org.junit.Test

class DurationUtilsUTest {

    @Test
    fun verifyConvertDateTimeToHoursMinutes() {
        // Given
        val dateTime = "2017-12-09T14:12:51Z"
        val expected = "2:12 PM"

        // When
        val result = DurationUtils.convertDateTimeToHoursMinutes(dateTime)

        // Verify
        assertEquals(expected, result)
    }

    @Test
    fun verifyConvertSecondToHoursMinutesSecond() {
        // Given
        val seconds: Long = 19427000
        val expected = "5:23:47"

        // When
        val result = DurationUtils.convertSecondToHoursMinutesSeconds(seconds)

        // Verify
        assertEquals(expected, result)
    }

}