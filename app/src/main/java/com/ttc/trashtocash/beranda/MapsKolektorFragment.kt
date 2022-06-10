package com.ttc.trashtocash.beranda

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceLikelihood
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.ttc.trashtocash.R
import com.ttc.trashtocash.databinding.FragmentMapsKolektorBinding

class MapsKolektorFragment : Fragment()/*, OnMapReadyCallback, GoogleMap.OnMarkerClickListener*/ {

    private var _binding: FragmentMapsKolektorBinding? = null
    private val binding get() = _binding!!

    private lateinit var map: GoogleMap

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    //new PlacesClient instance
    private var placesClient = Places.createClient(requireContext())
    private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsKolektorBinding.inflate(inflater, container, false)

        //getCurrentLocation()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun getCurrentLocation(){

        // Use fields to define the data types to return.
        val placeFields: List<Place.Field> = listOf(Place.Field.NAME)

        // Use the builder to create a FindCurrentPlaceRequest.
        val request: FindCurrentPlaceRequest = FindCurrentPlaceRequest.newInstance(placeFields)

        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {

            val placeResponse = placesClient.findCurrentPlace(request)
            placeResponse.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val response = task.result
                    for (placeLikelihood: PlaceLikelihood in response?.placeLikelihoods ?: emptyList()) {
                        Log.i(
                            "TAG",
                            "Place '${placeLikelihood.place.name}' has likelihood: ${placeLikelihood.likelihood}"
                        )
                    }
                } else {
                    val exception = task.exception
                    if (exception is ApiException) {
                        Log.e("TAG", "Place not found: ${exception.statusCode}")
                    }
                }
            }
        } else {
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }


    }

    /*override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap!!
        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } ==
            PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true

            fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
                if(location != null){
                    lastLocation = location
                    val currentLatLong = LatLng(location.latitude, location.longitude)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 15f))
                }
            }

        }else{
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
        }

        map.uiSettings.apply {
            isZoomControlsEnabled = false
        }

    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
        val arifLocation = LatLng(-5.119827559680111, 119.41682145191153)
        val debiLocation = LatLng(-5.119888008993758, 119.41535347718606)

        val arifMarker = map.addMarker(MarkerOptions()
            .position(arifLocation)
            .title("Arif Putra W.")
            .icon(fromVectortoBitmap(R.drawable.ic_greenuserloc, Color.parseColor("#1CAE81"))))
        val debiMarker = map.addMarker(MarkerOptions()
            .position(debiLocation)
            .title("Debi Rizky R.")
            .icon(fromVectortoBitmap(R.drawable.ic_greenuserloc, Color.parseColor("#1CAE81"))))

        arifMarker?.tag = "Penyetor"
        debiMarker?.tag = "Penyetor"
    }

    private fun fromVectortoBitmap(id: Int, color: Int): BitmapDescriptor{
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
    }*/
}