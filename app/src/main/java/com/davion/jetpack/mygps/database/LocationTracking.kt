package com.davion.jetpack.mygps.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_tracking")
data class LocationTracking(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo
    var time: String,
    @ColumnInfo
    var date: String,
    @ColumnInfo
    var address: String,
    @ColumnInfo
    var lattitude: Double,
    @ColumnInfo
    var longtitude: Double,
)