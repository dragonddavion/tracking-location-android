package com.davion.jetpack.mygps

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

private const val PERMISSION_ID = 1

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<LocationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTime.setOnClickListener {
            val time = timeDelay.text.toString()
            Log.d("Davion", "time delay: $time")
            viewModel.setTime(time.toLong())
        }

        getLocation.setOnClickListener {
            runWorker()
        }

        cancelButton.setOnClickListener {
            viewModel.cancelWorker()
        }
    }

    private fun runWorker() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                viewModel.runWorker()
            }
            else {
                Toast.makeText(this, "Please Turn on Your device Location", Toast.LENGTH_SHORT).show()
            }
        } else {
            requestPermission()
        }
    }

    /**
     * @return true if we have permission
     * @return false if not
     * */
    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_ID)
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }


    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Davion", "You have the Permission")
            } else {
                Log.d("Davion", "You dont have the Permission")
            }
        }
    }
}