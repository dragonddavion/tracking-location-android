package com.davion.jetpack.mygps

fun getDelayTime(): Long {
    val time = Preference.getString(PreferenceKey.STORE_TIME_DELAY, "10")
    return time?.toLong() ?: 10L
}