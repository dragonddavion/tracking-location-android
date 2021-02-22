package com.davion.jetpack.mygps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.davion.jetpack.mygps.LocationTrackingRepository
import com.davion.jetpack.mygps.LocationWorker
import com.davion.jetpack.mygps.util.Preference
import com.davion.jetpack.mygps.util.PreferenceKey
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application, private val repository: LocationTrackingRepository) : AndroidViewModel(application){
    fun setTime(time: Long) {
        Preference.setString(PreferenceKey.STORE_TIME_DELAY, time.toString())
    }

    fun runWorker() {
        LocationWorker.runAt(getApplication(), repository)
    }

    fun cancelWorker() {
        LocationWorker.cancel(getApplication())
    }
}