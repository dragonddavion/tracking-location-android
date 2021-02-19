package com.davion.jetpack.mygps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.davion.jetpack.mygps.LocationWorker
import com.davion.jetpack.mygps.util.Preference
import com.davion.jetpack.mygps.util.PreferenceKey

class HomeViewModel(application: Application) : AndroidViewModel(application){
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