package com.example.bd0631.goldseeker.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder

class LocationHelper {

  fun getCoordinates(locationName: String?, context: Context): List<Address>? {
    val geocoder = Geocoder(context)
    val address: List<Address>?
    address = geocoder.getFromLocationName(locationName, 1)
    if (address.isNotEmpty()) {
      return address
    }
    return null
  }

}

