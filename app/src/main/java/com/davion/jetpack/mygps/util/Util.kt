package com.davion.jetpack.mygps

import com.davion.jetpack.mygps.util.Preference
import com.davion.jetpack.mygps.util.PreferenceKey

fun getDelayTime(): Long {
    val time = Preference.getString(PreferenceKey.STORE_TIME_DELAY, "10")
    return time?.toLong() ?: 10L
}