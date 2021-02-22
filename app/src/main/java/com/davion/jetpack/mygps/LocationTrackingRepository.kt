package com.davion.jetpack.mygps

import com.davion.jetpack.mygps.database.LocationTrackingDao
import javax.inject.Inject

class LocationTrackingRepository @Inject constructor(private val database: LocationTrackingDao) {

}