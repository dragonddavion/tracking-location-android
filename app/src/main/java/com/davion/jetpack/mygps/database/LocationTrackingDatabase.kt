package com.davion.jetpack.mygps.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocationTracking::class], version = 1, exportSchema = false)
abstract class LocationTrackingDatabase : RoomDatabase() {
    abstract val locationTrackingDao : LocationTrackingDao
}