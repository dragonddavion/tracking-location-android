package com.davion.jetpack.mygps

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.davion.jetpack.mygps.util.getDelayTime
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope
import java.util.*
import java.util.concurrent.TimeUnit

@HiltWorker
class LocationWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: LocationTrackingRepository
) : CoroutineWorker(appContext, workerParams) {

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(appContext)
    }

    companion object {
        private const val POST_LOCATION_WORK_NAME = "location"

        fun runAt(context: Context, repository: LocationTrackingRepository) {
            Log.d("Davion", "repository: " + repository)
            val workManager = WorkManager.getInstance(context)

            val delayTime = getDelayTime()

            val workRequest = OneTimeWorkRequestBuilder<LocationWorker>()
                .setInitialDelay(delayTime, TimeUnit.SECONDS)
                .build()

            workManager.enqueueUniqueWork(
                POST_LOCATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
        }

        fun cancel(context: Context) {
            val workManager = WorkManager.getInstance(context)
            workManager.cancelUniqueWork(POST_LOCATION_WORK_NAME)
        }
    }

    override suspend fun doWork(): Result = coroutineScope {
        val context = applicationContext

        try {
            //Todo post data to server
            Log.d("Davion", "post Data to Server: " + checkPermission())
            getLastLocation()
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount > 3) {
                return@coroutineScope Result.success()
            }
            return@coroutineScope Result.retry()
        } finally {
            Log.d("Davion", "run continue")
            if (checkPermission()) {
                runAt(context, repository = repository)
            }
        }
    }

    private fun getLastLocation() {
        if (checkPermission()) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                val location: Location? = task.result
                if (location != null) {

                    val text =
                        "My Current Location is : Long: " + location.longitude + " , Lat: " + location.latitude + "\n" + getCityName(
                            location.latitude,
                            location.longitude
                        )
                    Log.d("Debug", "My Location:$text")
                }
            }
        }
    }

    /**
     * @return true if we have permission
     * @return false if not
     * */
    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun getCityName(lat: Double, long: Double): String {
        val cityName: String
        val countryName: String
        val geoCoder = Geocoder(applicationContext, Locale.getDefault())
        val address = geoCoder.getFromLocation(lat, long, 3)

        cityName = address[0].getAddressLine(0)
        countryName = address[0].countryName

        if (cityName.isNotEmpty() && countryName.isNotEmpty()) {
            Log.d("Debug:", "Your City: $cityName ; your Country $countryName")
            return cityName
        }
        return "No address found"
    }
}