package com.davion.jetpack.mygps

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationApplication: Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        applicationScope.launch {
            Preference.init(applicationContext)
        }
    }
}