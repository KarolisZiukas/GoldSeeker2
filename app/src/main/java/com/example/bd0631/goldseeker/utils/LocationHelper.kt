package com.example.bd0631.goldseeker.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.lang.Exception
import java.lang.reflect.InvocationTargetException

class LocationHelper {

  fun getCoordinates(locationName: String?, context: Context): List<Address>? {
    val geocoder = Geocoder(context)
    val address: List<Address>?

    return try {
      address = geocoder.getFromLocationName(locationName, 1)
      if (address.isNotEmpty()) {
        address
      } else {
        null
      }
    }
    catch (e: InvocationTargetException) {
      Log.e("GEOCODER EXCEPTION", e.cause.toString())
      null
    }
    catch (e: Exception) {
      Log.e("GEOCODER EXCEPTION", e.cause.toString())
      null
    }
  }

}

