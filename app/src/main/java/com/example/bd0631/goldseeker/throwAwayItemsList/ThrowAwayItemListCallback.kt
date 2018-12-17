package com.example.bd0631.goldseeker.throwAwayItemsList

import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation

interface ThrowAwayItemListCallback {

  fun onItemDeleted(pickUpLocation: PickUpLocation?)

  fun onItemSelected(pickUpLocation: PickUpLocation?)

}

