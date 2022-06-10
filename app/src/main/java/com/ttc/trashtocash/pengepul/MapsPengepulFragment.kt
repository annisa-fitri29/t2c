package com.ttc.trashtocash.pengepul

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ttc.trashtocash.R
import com.ttc.trashtocash.databinding.FragmentMapsPengepulBinding
import com.ttc.trashtocash.databinding.FragmentMapsTestBinding

class MapsPengepulFragment : Fragment() {

    private var _binding: FragmentMapsPengepulBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    private lateinit var map: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->

        map = googleMap!!

        map.uiSettings.apply {
            isZoomControlsEnabled = false
        }

        setupMap()

        val arifLocation = LatLng(-5.134783206230161, 119.42145176824305)
        val debiLocation = LatLng(-5.119888008993758, 119.41535347718606)

        val arifMarker = map.addMarker(MarkerOptions()
            .position(arifLocation)
            .title("Arif Putra W.")
            .icon(fromVectortoBitmap(R.drawable.ic_greenuserloc, Color.parseColor("#1CAE81"))))
        val debiMarker = map.addMarker(MarkerOptions()
            .position(debiLocation)
            .title("Amiruddin")
            .icon(fromVectortoBitmap(R.drawable.ic_greenuserloc, Color.parseColor("#1CAE81"))))

        val currentLatLong = debiLocation //(just for test)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 15f))
        arifMarker?.tag = "Penyetor"
        debiMarker?.tag = "Penyetor"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsPengepulBinding.inflate(inflater, container, false)

        //getCurrentLocation()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    //override fun onMapReady(googleMap: GoogleMap) {

    // }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupMap(){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            return
        }
        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                //map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 15f))

                //val urlDirection = "https://maps.googleapis.com/maps/api/directions/origin=${location.latitude},${location.longitude}&destination=-5.134783206230161,119.42145176824305&key=AIzaSyDTGHwClNSL1LhVDzh5XTJIUA9fmwYegVQ"
            }
        }
    }

    private fun fromVectortoBitmap(id: Int, color: Int): BitmapDescriptor {
        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources,id, null)
        if (vectorDrawable == null){
            Log.d("MapsKolektor","Resource not found.")
            return  BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0,0,canvas.width,canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}