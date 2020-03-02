package com.ksoft.rrkhan.androidweatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.ksoft.rrkhan.androidweatherapp.Adapter.ViewpagerAdapter
import com.ksoft.rrkhan.androidweatherapp.Common.Common

class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var coordinatorLayout: CoordinatorLayout? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback? = null
    private var locationRequest: LocationRequest? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coordinatorLayout = findViewById<View>(R.id.root_view) as CoordinatorLayout
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        if (toolbar != null) { // you can safely invoke methods on the Toolbar
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        //Request Permission
        Dexter.withActivity(this).withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    buildLocationRequest()
                    buildLocationCallBack()
                    if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return
                    }
                    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
                    //fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
                }
            }

            override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                Snackbar.make(coordinatorLayout!!, "Permission Denied", Snackbar.LENGTH_LONG).show()
            }
        }).check()
    }

    private fun buildLocationCallBack() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                Common.current_location = locationResult.lastLocation
                viewPager = findViewById<View>(R.id.view_pager) as ViewPager
                setupViewPager(viewPager)
                tabLayout = findViewById<View>(R.id.tabs) as TabLayout
                tabLayout!!.setupWithViewPager(viewPager)
                Log.d("Location", locationResult.lastLocation.latitude.toString() + "/" + locationResult.lastLocation.longitude)
            }
        }
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewpagerAdapter(supportFragmentManager)
        adapter.addFragment(TodayWeatherFragment.getInstance(), "Today")
        viewPager!!.adapter = adapter
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest!!.interval = 5000
        locationRequest!!.fastestInterval = 3000
        locationRequest!!.smallestDisplacement = 10.0f
    }
}