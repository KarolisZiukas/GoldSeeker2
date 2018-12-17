package com.example.bd0631.goldseeker.locations

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation
import com.example.bd0631.goldseeker.databinding.FragmentLocationsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_locations.*


class LocationsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

  lateinit var databinding: FragmentLocationsBinding
  lateinit var viewModel: LocationsViewModel

  private lateinit var map: GoogleMap
  var selected = false

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_locations, container, false)

    val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
    return databinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = (activity as LocationsActivity).obtainViewModel()
    databinding.viewModel = viewModel
    viewModel.isVisible.value = true

    fab_send_message.setOnClickListener {
      val sendIntent = Intent(Intent.ACTION_VIEW)
      sendIntent.data = Uri.parse("sms:" + tv_phone_number.text)
      startActivity(sendIntent)
    }
  }

  companion object {

    fun newInstance() = LocationsFragment()
  }

  override fun onMapReady(googleMap: GoogleMap) {
    viewModel.getThrowAwayItem()
        ?.observe(this, Observer<List<PickUpLocation>> {
          setMarkers(it, googleMap)
        })

  }

  private fun setMarkers(it: List<PickUpLocation>?, googleMap: GoogleMap) {
    map = googleMap
    for (item in it!!.listIterator()) {
      if (item.latitude != null && item.longitude != null) {
        map.addMarker(MarkerOptions()
            .position(LatLng(item.longitude!!, item.latitude!!))
            .title(item.Address)
            .snippet(item.id.toString()))
      }
    }
    if (it[0].latitude != null && it[0].longitude != null) {
      val cameraPosition = CameraPosition.Builder()
          .target(LatLng(it[0].longitude!!, it[0].latitude!!))
          .zoom(11f)
          .build()
      map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
      map.setOnMarkerClickListener(this)
    }

  }

  override fun onMarkerClick(marker: Marker?): Boolean {
    for (item in viewModel.throwAwayItemsList!!.value!!.listIterator()) {
      if (item.id.toString() == marker?.snippet) {
        tv_items_description.text = item.itemsList
        tv_address.text = item.Address
        tv_location_name.text = item.warehouseName
        tv_phone_number.text = item.phoneNumber
      }

    }
    viewModel.isVisible.value = false
    return false
  }

}
