package com.davion.jetpack.mygps.di

import android.app.Application
import androidx.room.Room
import com.davion.jetpack.mygps.LocationTrackingRepository
import com.davion.jetpack.mygps.database.LocationTrackingDao
import com.davion.jetpack.mygps.database.LocationTrackingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): LocationTrackingDatabase {
        return Room.databaseBuilder(application, LocationTrackingDatabase::class.java, "location_tracking_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationTrackingDao(database: LocationTrackingDatabase): LocationTrackingDao {
        return database.locationTrackingDao
    }

    @Provides
    @Singleton
    fun provideRepository(database: LocationTrackingDao): LocationTrackingRepository {
        return LocationTrackingRepository(database)
    }
}