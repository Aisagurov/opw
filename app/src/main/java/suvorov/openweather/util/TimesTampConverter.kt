package suvorov.openweather.util

import java.text.SimpleDateFormat
import java.util.*

fun Int.unixTimestampToDateTimeString(): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000.toLong()

        val outputDateFormat = SimpleDateFormat("dd", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)
    } catch (e: Exception) {
        e.message
    }
    return this.toString()
}

fun Int.unixTimestampToDayMonthString(): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000.toLong()

        val outputDateFormat = SimpleDateFormat("MM", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)
    } catch (e: Exception) {
        e.message
    }
    return this.toString()
}

fun Int.unixTimestampToDayWeekString(): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000.toLong()

        val outputDateFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)
    } catch (e: Exception) {
        e.message
    }
    return this.toString()
}

fun Int.unixTimestampToTimeString(): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000.toLong()

        val outputDateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)

        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)
    } catch (e: Exception) {
        e.message
    }
    return this.toString()
}