package com.tai.starwars.modules.utils

import java.text.SimpleDateFormat
import java.util.*

object DurationUtils {

    fun convertSecondToHoursMinutesSeconds(millis: Long): String {
        val seconds = millis.div(1000L)
        return String.format("%d:%d:%d", seconds / 3600, seconds % 3600 / 60, seconds % 60)
    }

    fun convertDateTimeToHoursMinutes(dateTime: String?): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.FRANCE)
        val formatter = SimpleDateFormat("h:mm a", Locale.FRANCE)
        return formatter.format(parser.parse(dateTime))
    }

}