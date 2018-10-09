package com.example.bd0631.goldseeker.locations

import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.databinding.FragmentLocationsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationsFragment : Fragment(), OnMapReadyCallback {

  private lateinit var mMap: GoogleMap
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

  override fun onMapReady(p0: GoogleMap) {
    mMap = p0
    val sydney = LatLng(-34.0, 151.0)
    mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
    mMap.addMarker(MarkerOptions().position(LatLng(51.1, 10.4)).title("Marker in Sydney"))
    mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(51.1, 10.4)))
    mMap.setOnMarkerClickListener {
      isVisible.value = true
//      if(!selected){
//        val constraintSet = ConstraintSet()
//        constraintSet.clone(context, R.layout.fragment_locations_1)
//        TransitionManager.beginDelayedTransition(v_parent)
//        constraintSet.applyTo(v_parent)
//        selected = true
//      }

//      TransitionManager.beginDelayedTransition(v_details)
//      with(ConstraintSet()) {
//        clone(context, R.layout.fragment_locations_1)
//        applyTo(v_details)
//      }
      false
    }
  }

//  private fun updateCardInvalidStatus() {
//    if (isSelectedCardBlacklisted) {
//      TransitionManager.beginDelayedTransition(w_root)
//      with(ConstraintSet()) {
//        clone(context, R.layout.view_card_picker_component)
//        setVisibility(R.id.tv_invalid, View.VISIBLE)
//        applyTo(w_root)
//      }
//    } else {
//      tv_invalid.visibility = View.GONE
//    }
//  }
}
