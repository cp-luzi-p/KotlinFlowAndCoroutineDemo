package com.example.kotlinflowandcoroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.kotlinflowandcoroutinesexample.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_maps.*
import com.google.android.gms.maps.CameraUpdate




class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

       val latitude = intent.getStringExtra("Lat")?.toDouble()
        val longitude = intent.getStringExtra("Long")?.toDouble()
        val countryName = intent.getStringExtra("country")

        // Add a marker in Sydney and move the camera
        val place = LatLng(latitude!!,longitude!!)
        map.addMarker(MarkerOptions().position(place).title(countryName))
        map.moveCamera(CameraUpdateFactory.newLatLng(place))

        val zoom = CameraUpdateFactory.zoomTo(4f)
        map.animateCamera(zoom)
    }

    override fun onMarkerClick(p0: Marker): Boolean {

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        return true
    }
}