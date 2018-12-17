package com.example.bd0631.goldseeker.throwAwayItemsList

import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation

class ThrowAwayItemListItemViewModel(
    val pickUpLocation: PickUpLocation?,
    val pickUpLacationsRepo: PickUpLacationsRepo,
    val imageFile: Bitmap?,
    val throwAwayItemListCallback: ThrowAwayItemListCallback) : ViewModel() {

  fun onItemSelected() {
      throwAwayItemListCallback.onItemSelected(pickUpLocation)
  }
}