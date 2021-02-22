package com.davion.jetpack.mygps.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationTrackingDao {
    @Query(value = "SELECT * from location_tracking")
    fun getAllTracking(): LiveData<List<LocationTracking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracking(dailyCost: LocationTracking)
}