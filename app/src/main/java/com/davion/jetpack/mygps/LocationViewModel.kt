package com.davion.jetpack.mygps

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    fun setTime(time: Long) {
        Preference.setString(PreferenceKey.STORE_TIME_DELAY, time.toString())
    }

    fun runWorker() {
        LocationWorker.runAt(getApplication())
    }

    fun cancelWorker() {
        LocationWorker.cancel(getApplication())
    }
}