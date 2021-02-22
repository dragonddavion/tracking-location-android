package com.davion.jetpack.mygps.util

import java.text.SimpleDateFormat
import java.util.*

fun getDelayTime(): Long {
    val time = Preference.getString(PreferenceKey.STORE_TIME_DELAY, "10")
    return time?.toLong() ?: 10L
}

fun getCurrentDate(): String {
    val calendar: Calendar = Calendar.getInstance()
    val time = calendar.time
    val parser = SimpleDateFormat("'Date: 'yyyy-MM-dd' Time: 'HH:mm:ss", Locale.getDefault())
    return parser.format(time)
}