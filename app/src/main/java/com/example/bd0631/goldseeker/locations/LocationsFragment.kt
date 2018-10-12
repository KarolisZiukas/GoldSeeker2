package com.example.bd0631.goldseeker.locations

import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.databinding.FragmentLocationsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationsFragment : Fragment(), OnMapReadyCallback {

  private lateinit var map: GoogleMap
  lateinit var databinding: FragmentLocationsBinding
  val isVisible = MutableLiveData<Boolean>()
  var selected = false

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_locations, container, false)

    val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
    return databinding.root
  }

  companion object {

    fun newInstance() = LocationsFragment()
  }

  override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap
    val sydney = LatLng(-34.0, 151.0)
    map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").snippet("23432432"))
    map.addMarker(MarkerOptions().position(LatLng(51.1, 10.4)).title("Marker in Sydney"))
    map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(51.1, 10.4)))
    map.setOnMarkerClickListener {
      Toast.makeText(context, it.snippet, Toast.LENGTH_LONG).show()
      isVisible.value = true
      false
    }
  }
}
